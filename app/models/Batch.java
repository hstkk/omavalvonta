package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import models.dynamicforms.Form;
import models.dynamicforms.Form_;
import models.dynamicforms.Results;
import models.dynamicforms.Results_;
import models.helpers.Dao;
import models.helpers.UserModel;

import org.hibernate.envers.Audited;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import play.data.format.Formats;
import play.data.validation.Constraints.Required;
import play.data.validation.Validation;
import play.data.validation.ValidationError;
import play.i18n.Messages;
import utils.Converter;
import utils.Helper;

@Entity
@Audited
public class Batch extends UserModel {

	public interface All {
	}

	public interface Step2 {
	}

	public final static Dao<Batch, Long> dao = new Dao<Batch, Long>(Batch.class);

	@Required(groups = { All.class })
	@Valid
	@ManyToOne
	public Product product;

	@Required(groups = { All.class, Step2.class })
	@NotNull(message = "", groups = { All.class, Step2.class })
	@Formats.DateTime(pattern = "dd.MM.yyyy")
	public Date date;

	@Required(groups = { All.class, Step2.class })
	@NotNull(message = "", groups = { All.class, Step2.class })
	@Valid
	@OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
	public List<IngredientSupplyBatch> ingredientSupplies = new ArrayList<IngredientSupplyBatch>();

	@OneToOne(mappedBy = "batch", fetch = FetchType.LAZY)
	public FinalProduct finalProduct;

	@ManyToMany(mappedBy = "batches", fetch = FetchType.LAZY)
	public List<Results> results;

	public Batch() {
	}

	public Batch(Product product) {
		this.product = product;
	}

	@Override
	public boolean onCreate() {
		this.product = Product.dao.getReference(this.product);
		return super.onCreate();
	}

	public String toString() {
		String separator = Helper.getOrElse("batch.separator");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Converter.dateToString(this.date,
				Helper.getOrElse("batch.date")));
		stringBuilder.append(separator);
		stringBuilder.append(this.product.no);
		stringBuilder.append(separator);
		stringBuilder.append(this.id);
		return stringBuilder.toString();
	}

	public boolean bind(User user, Product product) {
		this.user = user;
		this.product = product;
		if (ingredientSupplies.isEmpty())
			return false;

		List<IngredientSupplyBatch> newIngredientSupplies = new ArrayList<IngredientSupplyBatch>();

		for (IngredientSupplyBatch ingredientSupplyBatch : this.ingredientSupplies) {
			if (ingredientSupplyBatch == null
					|| (ingredientSupplyBatch.amount != null && ingredientSupplyBatch.amount < 0))
				return false;
			if (ingredientSupplyBatch.amount != null
					&& ingredientSupplyBatch.amount > 0) {
				if (!ingredientSupplyBatch.useAmount())
					return false;
				ingredientSupplyBatch.bind(this);
				newIngredientSupplies.add(ingredientSupplyBatch);
			}
		}

		if (!newIngredientSupplies.isEmpty()) {
			this.ingredientSupplies = newIngredientSupplies;
			return true;
		}
		return false;
	}

	public static List<Batch> findByUser(User user) {
		if (user == null)
			return null;
		CriteriaBuilder criteriaBuilder = dao.getCriteriaBuilder();
		CriteriaQuery<Batch> query = criteriaBuilder.createQuery(Batch.class);
		Root<Batch> root = query.from(Batch.class);
		query.where(criteriaBuilder.equal(root.get(Batch_.user), user));
		return dao.findAllBy(query);
	}

	@Transient
	private Map<String, IngredientSupply> ingredientSupplyMap;

	public IngredientSupply getIngredientSupply(String ingredientSupplyId) {
		if (ingredientSupplyMap == null) {
			ingredientSupplyMap = new LinkedHashMap<String, IngredientSupply>();
			if (this.product != null && this.product.ingredients != null)
				for (Ingredient ingredient : this.product.ingredients)
					if (ingredient != null
							&& ingredient.ingredientSupllies != null)
						for (IngredientSupply ingredientSupply : ingredient.ingredientSupllies)
							ingredientSupplyMap.put(
									ingredientSupply.id.toString(),
									ingredientSupply);
		}
		return ingredientSupplyMap.get(ingredientSupplyId);
	}

	public play.data.Form<Batch> getPrefilledForm() {
		if (this.product != null && this.product.ingredients != null)
			for (Ingredient ingredient : this.product.ingredients)
				if (ingredient != null) {
					List<IngredientSupply> ingredientSupplies = IngredientSupply
							.options(ingredient);
					if (ingredientSupplies != null) {
						for (IngredientSupply ingredientSupply : ingredientSupplies) {
							IngredientSupplyBatch ingredientSupplyBatch = new IngredientSupplyBatch(
									ingredientSupply);
							this.ingredientSupplies.add(ingredientSupplyBatch);
						}
					}
				}
		play.data.Form<Batch> form = new play.data.Form<Batch>(Batch.class);
		return form.fill(this);
	}

	public String getStatus() {
		if (this.finalProduct != null)
			return this.finalProduct.toString();
		return Messages.get("status.wip");
	}

	public static Map<String, String> optionsByProductAndForm(Product product,
			Form form) {
		if (product == null || form == null)
			return new LinkedHashMap<String, String>();

		CriteriaBuilder criteriaBuilder = dao.getCriteriaBuilder();
		CriteriaQuery<Batch> query = criteriaBuilder.createQuery(Batch.class);
		Root<Batch> root = query.from(Batch.class);
		Join<Batch, Product> join = root.join(Batch_.product);

		Subquery<Results> subqueryA = query.subquery(Results.class);
		Root<Results> subRoot = subqueryA.from(Results.class);
		Join<Results, Batch> subJoinA = subRoot.join(Results_.batches);
		Join<Results, Form> subJoinB = subRoot.join(Results_.form);
		subqueryA.select(subRoot);

		Subquery<FinalProduct> subqueryB = query.subquery(FinalProduct.class);
		Root<FinalProduct> subRootB = subqueryB.from(FinalProduct.class);
		Join<FinalProduct, Batch> subJoinC = subRootB.join(FinalProduct_.batch);
		subqueryB.select(subRootB);

		query.where(criteriaBuilder.and(
				criteriaBuilder.equal(join.get(Product_.id), product.id),
				criteriaBuilder.not(criteriaBuilder.exists(subqueryA.where(
						criteriaBuilder.equal(subJoinA.get(Batch_.id),
								root.get(Batch_.id)),
						criteriaBuilder.equal(subJoinB.get(Form_.id), form.id)))),
				criteriaBuilder.not(criteriaBuilder.exists(subqueryB
						.where(criteriaBuilder.equal(subJoinC.get(Batch_.id),
								root.get(Batch_.id)))))));

		return dao.options(query);
	}

	private Map<String, List<ValidationError>> _validate() {
		Map<String, List<ValidationError>> errors = new HashMap<String, List<ValidationError>>();

		SpringValidatorAdapter validator = new SpringValidatorAdapter(
				Validation.getValidator());
		Set<ConstraintViolation<Batch>> violations = validator.validate(this);
		for (ConstraintViolation<Batch> violation : violations) {
			String field = violation.getPropertyPath().toString();
			String error = violation.getMessage();
			List<ValidationError> list = new ArrayList<ValidationError>();
			list.add(new ValidationError(field, error));
			errors.put(field, list);
		}

		return errors;
	}

	public Map<String, List<ValidationError>> validate() {
		Map<String, List<ValidationError>> errors = _validate();
		int i = 0;
		for (IngredientSupplyBatch ingredientSupplyBatch : this.ingredientSupplies) {
			if (ingredientSupplyBatch.amount != null)
				if (ingredientSupplyBatch.amount < 0) {
					List<ValidationError> list = new ArrayList<ValidationError>();
					list.add(new ValidationError("negative", Messages
							.get("batch.amountNegative")));
					errors.put("ingredientSupplies[" + i + "].amount", list);
				} else if (ingredientSupplyBatch.amount > 0) {
					IngredientSupply ingredientSupply = IngredientSupply.dao
							.findById(ingredientSupplyBatch.ingredientSupply.id);
					if (ingredientSupply != null
							&& !ingredientSupply
									.isAmountAvailable(ingredientSupplyBatch.amount)) {
						List<ValidationError> list = new ArrayList<ValidationError>();
						list.add(new ValidationError("tooMuch", Messages
								.get("batch.amountTooMuch")));
						errors.put("ingredientSupplies[" + i + "].amount", list);
					}
				}
			i++;
		}
		if (!errors.isEmpty())
			return errors;
		return null;
	}
}
