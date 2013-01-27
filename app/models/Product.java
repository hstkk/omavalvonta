package models;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import models.dynamicforms.Form;
import models.helpers.Crud;
import models.helpers.UserModel;

import play.data.validation.Constraints.*;


@Entity
@Audited
public class Product extends UserModel {
	public final static Crud<Product, Long> crud = new Crud<Product, Long>(Product.class);

	@Required
	@NotNull
	@Column(unique = true)
	public Long no;

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
		return name + " (" + no.toString() + ")";
	}
}
