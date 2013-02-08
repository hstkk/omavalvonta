package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Target;
import org.hibernate.envers.Audited;

import models.dynamicforms.Form;
import models.helpers.JpaModel;
import models.helpers.Page;

import play.Play;
import play.data.format.*;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;
import play.db.jpa.JPA;

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

	public IngredientAmount(IngredientSupply ingredientSupply, Double amount) {
		this.ingredientSupply = ingredientSupply;
		this.amount = amount;
		ingredientSupply.amountAvailable = ingredientSupply.amountAvailable
				- amount;
		ingredientSupply.update();
		this.save();
	}
}