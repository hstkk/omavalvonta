package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import models.dynamicforms.Form;
import models.helpers.Dao;
import models.helpers.UserModel;

import org.hibernate.envers.Audited;

import play.data.validation.Constraints.Required;

@Entity
@Audited
public class Product extends UserModel {
	public final static Dao<Product, Long> dao = new Dao<Product, Long>(
			Product.class);

	// TODO check unique
	@Required
	@NotNull
	@Column(unique = true)
	public int no;

	// TODO check unique
	@Required
	@NotNull
	@Column(unique = true)
	public String name;

	@Lob
	public String description;

	@ManyToMany(cascade = CascadeType.ALL)
	@Valid
	public List<Ingredient> ingredients = new ArrayList<Ingredient>();

	@ManyToMany(cascade = CascadeType.ALL)
	@Valid
	public List<Form> forms = new ArrayList<Form>();

	public String toString() {
		return name + " (" + no + ")";
	}

	@Override
	public void set() {
		ingredients = Ingredient.dao.getReference(ingredients);
		forms = Form.dao.getReference(forms);
	}
}
