package models;

import java.util.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import models.dynamicforms.Form;

import org.hibernate.envers.Audited;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Batch extends JpaModel {
	@Required
	@NotNull
	public Date created;

	@Required
	@Valid
	@OneToOne
	public Product product;

	public Batch() {
	}

	public static Batch findById(Long id) {
		if (id == null)
			return null;
		try {
			return JPA.em().find(Batch.class, id);
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Batch> findAll() {
		try {
			List<Batch> batch = JPA.em().createQuery("from Batch order by name")
					.getResultList();
			return batch;
		} catch (Exception e) {
			return null;
		}
	}
}