package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import models.Batch.All;
import models.helpers.Model;
import org.hibernate.envers.Audited;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;
import utils.Formats.LocalizedDouble;

@Entity
@Table(name = "IngredientSupply_Batch")
@Audited
public class IngredientSupplyBatch extends Model {
	@Required
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ingredientSupply_id", updatable = false)
	@Valid
	public IngredientSupply ingredientSupply;

	@Required(groups = { All.class })
	@NotNull(groups = { All.class })
	@ManyToOne
	@JoinColumn(name = "batch_id", updatable = false)
	@Valid
	public Batch batch;

	@Required
	@NotNull(groups = { All.class })
	@Min(0)
	@Column(updatable = false)
	@LocalizedDouble
	public Double amount;

	public boolean bind(Batch batch) {
		this.batch = batch;

		if (this.amount < 0)
			return false;

		this.ingredientSupply = IngredientSupply.dao
				.findById(this.ingredientSupply.id);
		if (this.ingredientSupply == null)
			return false;

		boolean success = this.ingredientSupply.isAvailable(this.amount);
		if (success)
			this.ingredientSupply.use(this.amount);

		return success;
	}
}
