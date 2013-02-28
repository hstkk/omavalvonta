package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import models.helpers.Crud;
import models.helpers.Model;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;

//TODO embed to Batch?
@Entity
public class IngredientAmount extends Model {
	public final static Crud<IngredientAmount, Long> crud = new Crud<IngredientAmount, Long>(
			IngredientAmount.class);

	@Required
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	public IngredientSupply ingredientSupply;

	@Required
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	public Batch batch;

	@Required
	@NotNull
	@Min(0)
	public Double amount;

	public IngredientAmount() {
	}

	// TODO check
	public IngredientAmount(IngredientSupply ingredientSupply, Double amount) {
		this.ingredientSupply = ingredientSupply;
		this.amount = amount;
		Double used = ingredientSupply.used + amount;
		if (used < ingredientSupply.amount) {
			ingredientSupply.used = used;
			IngredientSupply.crud.update(ingredientSupply);
			crud.create(this);
		}
		// TODO Rollback
	}
}