package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import models.helpers.Dao;
import models.helpers.UserModel;
import org.hibernate.envers.Audited;
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
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	public Ingredient ingredient;

	@Required
	@NotNull
	@Min(0)
	@LocalizedDouble
	public Double amount;

	@LocalizedDouble
	public Double used = 0.0;

	@Required
	@NotNull
	@Formats.DateTime(pattern = "dd.MM.yyyy")
	public Date received;

	@Required
	@NotNull
	@ManyToOne
	public Term unit;

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
	public void onCreate() {
		this.ingredient = Ingredient.dao.getReference(this.ingredient);
		this.unit = Term.dao.getReference(this.unit);
		super.onCreate();
	}

	public Date getBestBefore() {
		Calendar bestBefore = Calendar.getInstance();
		bestBefore.setTime(this.received);
		bestBefore.add(Calendar.DATE, this.ingredient.bestBefore);
		return bestBefore.getTime();
	}
}