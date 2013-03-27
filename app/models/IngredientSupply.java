package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import models.helpers.Crud;
import models.helpers.UserModel;
import org.hibernate.envers.Audited;
import play.data.format.Formats;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;
import utils.Converter;

@Entity
@Audited
public class IngredientSupply extends UserModel {
	public final static Crud<IngredientSupply, Long> crud = new Crud<IngredientSupply, Long>(
			IngredientSupply.class);

	@Required
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	public Ingredient ingredient;

	@Required
	@NotNull
	@Min(0)
	public Double amount;

	public Double used;

	@Required
	@NotNull
	@Formats.DateTime(pattern = "dd.MM.yyyy")
	public Date received;

	@Required
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	public Term unit;

	@OneToMany(mappedBy = "ingredientSupply")
	public List<IngredientSupplyBatch> batches = new ArrayList<IngredientSupplyBatch>();

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(ingredient);
		sb.append(" ");
		sb.append(amount);
		sb.append(" ");
		sb.append(unit);
		sb.append(" (");
		sb.append(Converter.dateToString(received));
		sb.append(")");
		return sb.toString();
	}

	public boolean isUsed() {
		return this.used >= this.amount;
	}

	@Override
	@PrePersist
	public void onCreate() {
		super.onCreate();

		this.ingredient = Ingredient.crud.getReference(this.ingredient);
		this.unit = Term.crud.getReference(this.unit);
	}

	public Date getBestBefore() {
		Calendar bestBefore = Calendar.getInstance();
		bestBefore.setTime(this.received);
		bestBefore.add(Calendar.DATE, this.ingredient.bestBefore);
		return bestBefore.getTime();
	}
}