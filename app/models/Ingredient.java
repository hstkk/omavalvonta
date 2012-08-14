package models;

import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

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
public class Ingredient extends JpaModel {

	/** The name. */
	@Required
	@NotNull
	public String name;

	/** The description. */
	public String description;

	// Days
	/** The best before. */
	@Min(1)
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
			Integer rows = (Integer) JPA.em()
					.createQuery("select count(*) from Ingredient")
					.getSingleResult();
			List<Ingredient> list = JPA.em()
					.createQuery("from Ingredient i order by i.name asc")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
			if (rows != null || list != null)
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return null;
	}
}
