package models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

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
		if (id == null)
			return null;
		try {
			return JPA.em().find(Ingredient.class, id);
		} catch (Exception e) {
			return null;
		}
	}
}
