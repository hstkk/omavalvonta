package models;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import models.helpers.JpaModel;
import models.helpers.Page;

import play.Play;
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
public class Ingredient extends JpaModel {

	/** The name. */
	@Required
	@NotNull
	@Column(unique = true)
	public String name;

	/** The description. */
	public String description;

	// Days
	/** The best before. */
	@Min(0)
	public Integer bestBefore;

	/**
	 * Find by id.
	 * 
	 * @param id
	 *            the id
	 * @return the ingredient
	 */
	public static Ingredient findById(Long id) {
		try {
			if (id != null)
				return JPA.em().find(Ingredient.class, id);
		} catch (Exception e) {
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}

	/**
	 * Page.
	 * 
	 * @param index
	 *            the index
	 * @return the page
	 */
	public static Page page(int index) {
		try {
			int size = Play.application().configuration().getInt("page.size");
			if (index < 1)
				index = 1;
			long rows = (long) JPA.em()
					.createQuery("select count(*) from Ingredient")
					.getSingleResult();
			List<Ingredient> list = JPA.em()
					.createQuery("from Ingredient i order by i.name asc")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
			if (rows > 0 && list != null && !list.isEmpty())
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return new Page(index, 0, 0, null);
	}

	public static Map<String, String> options() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		try {
			List<Ingredient> ingredients = JPA.em()
					.createQuery("from Ingredient order by name")
					.getResultList();
			for (Ingredient ingredient : ingredients)
				map.put(ingredient.id.toString(), ingredient.toString());
			return map;
		} catch (Exception e) {
			return map;
		}
	}

	public static String checkboxes(String id) {
		StringBuilder stringBuilder = new StringBuilder();
		List<Ingredient> checked = null;
		try {
			checked = Product.findById(Long.parseLong(id)).ingredients;
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
