package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import models.helpers.JpaModel;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;

@Entity
public class IngredientAmount extends JpaModel {

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
			this.save();
		}
		// TODO Rollback
	}
}