package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "Product_Ingredient")
public class ProductIngredient {
	@Id
	Long id;

	@JoinColumn(name = "product_id", updatable = false, nullable = false)
	@ManyToOne
	public Product product;

	@JoinColumn(name = "ingredient_id", updatable = false, nullable = false)
	@ManyToOne
	public Ingredient ingredient;

	public ProductIngredient(Product product, Ingredient ingredient) {
		this.product = product;
		this.ingredient = ingredient;
	}

	public static List<ProductIngredient> prepare(Product product,
			List<ProductIngredient> ingredients) {
		List<ProductIngredient> productIngredients = new ArrayList<ProductIngredient>();
		for (ProductIngredient productIngredient : ingredients) {
			Ingredient ingredient = Ingredient.dao
					.getReference(productIngredient.ingredient);
			if (ingredient != null)
				productIngredients.add(new ProductIngredient(product,
						ingredient));
		}
		return productIngredients;
	}
}