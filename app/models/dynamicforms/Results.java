package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import models.Batch;
import models.JpaModel;

import com.avaje.ebean.annotation.EnumValue;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
public class Results extends JpaModel {
	@Required
	@NotNull
	public Date updated;

	@Required
	@Enumerated(EnumType.STRING)
	@NotNull
	public FormType type;

	@Required
	public Boolean isReady;

	@Required
	@Valid
	@OneToMany
	@JoinTable(name = "ResultsResult", joinColumns = { @JoinColumn(name = "results_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "result_id", referencedColumnName = "id", unique = true) })
	public List<Result> results;

	@Required
	@Valid
	@OneToOne
	@NotNull
	public Batch batch;

	public Results() {
	}

	public static Results findById(int id) {
		return JPA.em().find(Results.class, id);
	}

	public static Results findByBatchAndType(Batch batch, String type) {
		if (batch == null || type == null || type.equals(""))
			return null;
		try {
			return (Results) JPA.em()
					.createQuery("Results r where r.batch = ? and r.type = ?")
					.setParameter(1, batch).setParameter(2, type)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	/*public static Results findByIdAndType(Long id, String type) {
		if (id == null)
			return null;
		try {
			// TODO optimize
			return (Results) JPA.em()
					.createQuery("Results r where r.id = ? and r.type = ?")
					.setParameter(1, id).setParameter(2, type)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}*/
}