package models.dynamicforms;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import play.data.validation.Constraints.Required;
import play.Play;
import play.db.jpa.JPA;
import models.helpers.JpaModel;
import models.helpers.Page;

// TODO: Auto-generated Javadoc
/**
 * The Class FormType.
 * 
 * @author Sami Hostikka <dev@01.fi>
 */
@Entity
public class FormType extends JpaModel {

	/** The name. */
	@Required
	@NotNull
	@Column(unique = true)
	public String name;

	/** The slug. */
	@Required
	@NotNull
	@Column(unique = true)
	public String slug;

	/**
	 * Find by id.
	 * 
	 * @param id
	 *            the id
	 * @return the form type
	 */
	public static FormType findById(Long id) {
		try {
			if (id != null)
				return JPA.em().find(FormType.class, id);
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
					.createQuery("select count(*) from FormType")
					.getSingleResult();
			List<FormType> list = JPA.em()
					.createQuery("from FormType f order by f.name asc")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
			if (rows != null || list != null)
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return null;
	}
}