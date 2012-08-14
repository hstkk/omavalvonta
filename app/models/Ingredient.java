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

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
@Entity
public class Ingredient extends JpaModel {
	@Required
	@NotNull
	String name;

	String description;

	// Days
	@Min(1)
	Integer bestBefore;

	public static Ingredient findById(Long id) {
		try {
			if (id != null)
				return JPA.em().find(Ingredient.class, id);
		} catch (Exception e) {
		}
		return null;
	}

	public String toString() {
		return name;
	}

	public static Page page(int index) {
		try {
			int size = Play.application().configuration().getInt("page.size");
			if (index < 1)
				index = 1;
			Integer rows = (Integer) JPA.em()
					.createQuery("select count(*) from Ingredient")
					.getSingleResult();
			List<Ingredient> list = JPA.em()
					.createQuery("from Ingredient i order by i.id asc")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
			if (rows != null || list != null)
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return null;
	}
}
