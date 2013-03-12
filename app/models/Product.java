package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import models.dynamicforms.Form;
import models.helpers.Crud;
import models.helpers.UserModel;

import org.hibernate.envers.Audited;

import play.data.validation.Constraints.Required;


@Entity
@Audited
public class Product extends UserModel {
	public final static Crud<Product, Long> crud = new Crud<Product, Long>(Product.class);

	// TODO check unique
	@Required
	@NotNull
	@Column(unique = true)
	public Long no;

	// TODO check unique
	@Required
	@NotNull
	@Column(unique = true)
	public String name;

	@Lob
	public String description;

	@ManyToMany(cascade = CascadeType.ALL)
	public List<Ingredient> ingredients = new ArrayList<Ingredient>();

	@ManyToOne(cascade = CascadeType.ALL)
	public Form form1;

	@ManyToOne(cascade = CascadeType.ALL)
	public Form form2;

	@ManyToOne(cascade = CascadeType.ALL)
	public Form form3;

	public String toString() {
		return name + " (" + no.toString() + ")";
	}

	@Override
	public void set() {
		ingredients = Ingredient.crud.getReference(ingredients);
		form1 = Form.crud.getReference(form1);
		form2 = Form.crud.getReference(form2);
		form3 = Form.crud.getReference(form3);
	}
}
