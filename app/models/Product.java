package models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
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
	public List<Ingredient> ingredients = new ArrayList<Ingredient>();

	@Transient
	public List<Long> ingredientIds = new ArrayList<Long>();

	@ManyToMany(cascade = CascadeType.ALL)
	public List<Form> forms = new ArrayList<Form>();

	@Transient
	public List<Long> formIds = new ArrayList<Long>();

	public String toString() {
		return name + " (" + no + ")";
	}

	@Override
	public void set() {
		this.ingredients = Ingredient.dao.getReferences(this.ingredientIds);
		this.forms = Form.dao.getReferences(this.formIds);
	}

	@PostLoad
	private void onPost() {
		for (Ingredient ingredient : this.ingredients)
			this.ingredientIds.add(ingredient.id);
		for (Form form : this.forms)
			this.formIds.add(form.id);
	}
}
