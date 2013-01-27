package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import models.helpers.Crud;
import models.helpers.UserModel;

import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;
import play.db.jpa.JPA;

// TODO: Auto-generated Javadoc
/**
 * The Class Ingredient.
 * 
 * @author Sami Hostikka <dev@01.fi>
 */
@Entity
@Audited
public class Ingredient extends UserModel {

	public final static Crud<Ingredient, Long> crud = new Crud<Ingredient, Long>(Ingredient.class);

	@Required
	@NotNull
	@Column(unique = true)
	public String name;

	public String description;

	// Days
	@Min(0)
	public Integer bestBefore;


	public String toString() {
		return name;
	}

	//TODO checkboxes
	public static String checkboxes(String id) {
		StringBuilder stringBuilder = new StringBuilder();
		List<Ingredient> checked = null;
		try {
			checked = Product.crud.findById(Long.parseLong(id)).ingredients;
		} catch (Exception e) {
		}
		try {
			List<Ingredient> ingredients = JPA.em()
					.createQuery("from Ingredient order by name")
					.getResultList();
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
		}
		return stringBuilder.toString();
	}
}
