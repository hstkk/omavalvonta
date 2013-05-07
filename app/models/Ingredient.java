package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@Required
	@NotNull
	public Integer bestBefore;

	@ManyToOne
	@Valid
	public Form form;

	@OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
	@Valid
	public List<IngredientSupply> ingredientSupllies = new ArrayList<IngredientSupply>();

	public String toString() {
		return name;
	}

	@Override
	public void onCreate() {
		set();
		super.onCreate();
	}

	@Override
	public void onUpdate() {
		set();
		super.onUpdate();
	}

	private void set() {
		this.form = Form.dao.getReference(this.form);
	}
}
