package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import models.helpers.Dao;
import models.helpers.UserModel;
import org.hibernate.envers.Audited;
import org.joda.time.DateTime;

import play.data.format.Formats;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;
import utils.Converter;
import utils.Formats.*;

@Entity
@Audited
public class IngredientSupply extends UserModel {
	public final static Dao<IngredientSupply, Long> dao = new Dao<IngredientSupply, Long>(
			IngredientSupply.class);

	@Required
	@NotNull(message = "")
	@ManyToOne(fetch = FetchType.LAZY)
	public Ingredient ingredient;

	@Required
	@NotNull(message = "")
	@Min(0)
	@LocalizedDouble
	public Double amount;

	@LocalizedDouble
	public Double used = 0.0;

	@Required
	@NotNull(message = "")
	@Formats.DateTime(pattern = "dd.MM.yyyy")
	public Date received;

	@Required
	@NotNull(message = "")
	@ManyToOne
	public Term unit;

	@Required
	@NotNull(message = "")
	@ManyToOne
	public Term producer;

	@OneToMany(mappedBy = "ingredientSupply", fetch = FetchType.LAZY)
	public List<IngredientSupplyBatch> batches = new ArrayList<IngredientSupplyBatch>();

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(ingredient);
		sb.append(" (");
		sb.append(Converter.dateToString(received));
		sb.append(")");
		return sb.toString();
	}

	public boolean isAmountAvailable(Double _amount) {
		if (_amount > 0 && !isUsed()) {
			return amountAvailable() >= _amount;
		}
		return false;
	}

	public boolean isUsed() {
		return this.used >= this.amount;
	}

	public Double amountAvailable() {
		if (isUsed())
			return (double) 0;
		return this.amount - this.used;
	}

	public String amountAvailableToString() {
		return utils.Converter.doubleToString(amountAvailable());
	}

	public void use(Double _amount) {
		this.used = this.used + _amount;
	}

	@Override
	public boolean onCreate() {
		this.ingredient = Ingredient.dao.getReference(this.ingredient);
		this.unit = Term.dao.getReference(this.unit);
		this.producer = Term.dao.getReference(this.producer);
		return super.onCreate();
	}

	@PreUpdate
	public void preUpdate() {
		super.onUpdate();
	}

	public Date getBestBefore() {
		Calendar bestBefore = Calendar.getInstance();
		bestBefore.setTime(this.received);
		bestBefore.add(Calendar.DATE, this.ingredient.bestBefore);
		return bestBefore.getTime();
	}

	public static List<IngredientSupply> findByUser(User user) {
		if (user == null)
			return null;
		CriteriaBuilder criteriaBuilder = dao.getCriteriaBuilder();
		CriteriaQuery<IngredientSupply> query = criteriaBuilder.createQuery(IngredientSupply.class);
		Root<IngredientSupply> root = query.from(IngredientSupply.class);
		query.where(criteriaBuilder.equal(root.get(IngredientSupply_.user), user));
		return dao.findAllBy(query);
	}

	/*public static List<IngredientSupply> options() {
		Calendar calendar = Calendar.getInstance();
		DateTime datetime = new DateTime();
		datetime.minusDays()
		
		calendar.setTime(this.received);
		calendar.
		bestBefore.add(Calendar.DATE, this.ingredient.bestBefore);
		Date since = calendar.getTime();

		CriteriaBuilder criteriaBuilder = dao.getCriteriaBuilder();
		CriteriaQuery<IngredientSupply> query = criteriaBuilder.createQuery(IngredientSupply.class);
		Root<IngredientSupply> root = query.from(IngredientSupply.class);
		query.where(criteriaBuilder.equal(root.get(IngredientSupply_.user), user));
		return dao.findAllBy(query);
	}*/
}