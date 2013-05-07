package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
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

	@OneToMany(mappedBy = "product", orphanRemoval = true)
	// @LazyCollection(LazyCollectionOption.FALSE)
	public List<ProductIngredient> ingredients = new ArrayList<ProductIngredient>();

	@OneToMany(mappedBy = "product", orphanRemoval = true)
	public List<ProductForm> forms = new ArrayList<ProductForm>();

	public String toString() {
		return name + " (" + no + ")";
	}

	@PrePersist
	@PreUpdate
	private void onPre() {
		System.out
				.println("*********************************************************************************");
		this.ingredients = ProductIngredient.prepare(this, this.ingredients);
		this.forms = ProductForm.prepare(this, this.forms);
		System.out
				.println("*********************************************************************************");
	}
}
