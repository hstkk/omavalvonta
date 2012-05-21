package models;

import javax.persistence.*;
import play.db.jpa.*;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
@MappedSuperclass
public class JpaModel {
	@Id
	@GeneratedValue
	public Long id;

	public void save() {
		try {
			this.id = id;
			JPA.em().persist(this);
		} catch (Exception e) {
		}
	}

	public void delete() {
		try {
			JPA.em().remove(this);
		} catch (Exception e) {
		}
	}

	public void update() {
		try {
			this.id = id;
			JPA.em().merge(this);
		} catch (Exception e) {
		}
	}
}