package models;

import javax.persistence.*;

import models.dynamicforms.Field;

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
	public int id;

	public void save() {
		try {
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
			JPA.em().merge(this);
		} catch (Exception e) {
		}
	}
}