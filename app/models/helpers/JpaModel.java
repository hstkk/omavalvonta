package models.helpers;

import javax.persistence.*;
import play.db.jpa.*;

/**
 * The Class JpaModel.
 * 
 * @author Sami Hostikka <dev@01.fi>
 */
@MappedSuperclass
public class JpaModel {

	/** The id. */
	@Id
	@GeneratedValue
	@Column(updatable=false)
	public Long id;

	/**
	 * Save.
	 * 
	 * @return true, if successful
	 */
	public boolean save() {
		try {
			JPA.em().persist(this);
			return true;
		} catch (Exception e) {
			System.out.println("\n\n" + e + "\n\n");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Delete.
	 * 
	 * @return true, if successful
	 */
	public boolean delete() {
		try {
			JPA.em().remove(this);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Update.
	 * 
	 * @return true, if successful
	 */
	public boolean update() {
		try {
			JPA.em().merge(this);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
}