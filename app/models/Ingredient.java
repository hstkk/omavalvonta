package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import models.dynamicforms.Form;
import models.helpers.Dao;
import models.helpers.UserModel;

import org.hibernate.envers.Audited;

import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;

/**
 * The Class Ingredient.
 * 
 * @author Sami Hostikka <dev@01.fi>
 */
@Entity
@Audited
public class Ingredient extends UserModel {
	public final static Dao<Ingredient, Long> dao = new Dao<Ingredient, Long>(
			Ingredient.class);

	@Required
	@NotNull
	@Column(unique = true)
	public String name;

	public String description;

	// Days
	@Min(0)
	public Integer bestBefore;

	@ManyToOne
	@Valid
	public Form form;

	@OneToMany(mappedBy = "ingredient")
	@Valid
	public List<IngredientSupply> ingredientSupllies;

	public String toString() {
		return name;
	}

	@PrePersist
	@PreUpdate
	private void onPre() {
		this.form = Form.dao.getReference(this.form);
	}
}
