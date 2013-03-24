package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import models.helpers.Model;
import org.hibernate.envers.Audited;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;

@Entity
@Table(name = "IngredientSupply_Batch")
@Audited
public class IngredientSupplyBatch extends Model {
	@Required
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ingredientSupply_id", updatable = false)
	public IngredientSupply ingredientSupply;

	@Required
	@NotNull
	@ManyToOne
	@JoinColumn(name = "batch_id", updatable = false)
	public Batch batch;

	@Required
	@NotNull
	@Min(0)
	@Column(updatable = false)
	public Double amount;
}
