package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import models.Batch.All;
import org.hibernate.envers.Audited;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;
import utils.Formats.LocalizedDouble;

@Entity
@Table(name = "IngredientSupply_Batch")
@Audited
public class IngredientSupplyBatch {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false)
	public Long id;

	@Required
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ingredientSupply_id", updatable = false)
	public IngredientSupply ingredientSupply;

	@Required(groups = { All.class })
	@NotNull(groups = { All.class })
	@ManyToOne
	@JoinColumn(name = "batch_id", updatable = false)
	public Batch batch;

	@Required
	@NotNull(groups = { All.class })
	@Min(0)
	@Column(updatable = false, nullable = false)
	@Valid
	@LocalizedDouble
	public Double amount;

	public IngredientSupplyBatch() {
	}

	public IngredientSupplyBatch(IngredientSupply ingredientSupply) {
		this.ingredientSupply = ingredientSupply;
	}

	public void bind(Batch batch) {
		this.batch = batch;
	}

	public boolean useAmount() {
		this.ingredientSupply = IngredientSupply.dao
				.findById(this.ingredientSupply.id);
		if (this.ingredientSupply == null)
			return false;

		boolean success = this.ingredientSupply.isAmountAvailable(this.amount);
		if (success)
			this.ingredientSupply.use(this.amount);

		return success;
	}
}
