package models;

import javax.persistence.*;
import play.db.jpa.*;

//@Entity
public class Model {
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