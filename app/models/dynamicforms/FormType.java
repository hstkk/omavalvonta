package models.dynamicforms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import play.data.validation.Constraints.Required;
import play.Play;
import play.db.jpa.JPA;
import models.helpers.JpaModel;
import models.helpers.Page;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
@Entity
public class FormType extends JpaModel {
	@Required
	@NotNull
	@Column(unique = true)
	public String name;

	@Required
	@NotNull
	@Column(unique = true)
	public String slug;

	public static FormType findById(Long id) {
		try {
			if (id != null)
				return JPA.em().find(FormType.class, id);
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
					.createQuery("select count(*) from FormType")
					.getSingleResult();
			List<FormType> list = JPA.em()
					.createQuery("from FormType i order by i.name asc")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
			if (rows != null || list != null)
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return null;
	}
}