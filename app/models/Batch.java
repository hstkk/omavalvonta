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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import models.dynamicforms.Form;
import models.dynamicforms.Results;
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

	@OneToMany(mappedBy = "form", fetch = FetchType.LAZY)
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
				if (ingredient != null && ingredient.ingredientSupllies != null)
					for (IngredientSupply ingredientSupply : ingredient.ingredientSupllies) {
						IngredientSupplyBatch ingredientSupplyBatch = new IngredientSupplyBatch(
								ingredientSupply);
						this.ingredientSupplies.add(ingredientSupplyBatch);
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
		query.where(criteriaBuilder.equal(root.get(Batch_.product).get("id"),
				product.id)
		// ,criteriaBuilder.isNull(root.get(Batch_.finalProduct).get("destiny"))
		);
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
			if (ingredientSupplyBatch.amount != null
					&& ingredientSupplyBatch.amount < 0) {
				List<ValidationError> list = new ArrayList<ValidationError>();
				list.add(new ValidationError("negative", Messages
						.get("batch.amountNegative")));
				errors.put("ingredientSupplies[" + i + "].amount", list);
			}

			i++;
		}
		if (!errors.isEmpty())
			return errors;
		return null;
	}
}
