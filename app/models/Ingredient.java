package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
	public Form form;

	@OneToMany(mappedBy = "ingredient")
	public List<IngredientSupply> ingredientSupllies;

	public String toString() {
		return name;
	}

	@PrePersist
	@PreUpdate
	private void onPre() {
		this.form = Form.dao.getReference(this.form);
	}

	// TODO checkboxes
	public static String checkboxes(String id) {
		StringBuilder stringBuilder = new StringBuilder();
		List<Ingredient> checked = null;
		try {
			checked = Product.dao.findById(Long.parseLong(id)).ingredients;
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			List<Ingredient> ingredients = dao.findAll();
			int i = 0;
			for (Ingredient ingredient : ingredients) {
				stringBuilder.append("<label class=\"checkbox\">");
				stringBuilder
						.append("<input type=\"checkbox\" name=\"ingredientIds[");
				stringBuilder.append(i);
				stringBuilder.append("]\" value=\"");
				stringBuilder.append(ingredient.id);
				stringBuilder.append("\"");
				if (checked != null && checked.contains(ingredient))
					stringBuilder.append(" checked=\"checked\"");
				stringBuilder.append(">");
				stringBuilder.append(ingredient.toString());
				stringBuilder.append("</label>");
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
}
