package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import models.Batch;
import models.JpaModel;

import com.avaje.ebean.annotation.EnumValue;

import forms.dynamicforms.Fieldset;

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
	public Boolean isReady = false;

	@Required
	// @Valid
	@OneToMany
	@JoinTable(name = "ResultsResult", joinColumns = { @JoinColumn(name = "results_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "result_id", referencedColumnName = "id", unique = true) })
	public List<Result> results;

	@Required
	// @Valid
	@ManyToOne
	@NotNull
	public Batch batch;

	public Results() {
	}

	public Results(Batch batch, List<Fieldset> values, FormType type) {
		this.batch = batch;
		this.updated = new Date();
		this.type = type;
		this.results = new ArrayList<Result>();
		System.out.println(values.size());
		for (Fieldset value : values)
			results.add(new Result(value));
	}

	public static Results findById(int id) {
		return JPA.em().find(Results.class, id);
	}

	public static Results findByBatchAndType(Batch batch, FormType type) {
		if (batch == null || type == null || type.equals(""))
			return null;
		try {
			return (Results) JPA
					.em()
					.createQuery(
							"from Results r where r.batch = ? and r.type.toString like ?")
					.setParameter(1, batch).setParameter(2, type.toString())
					.getSingleResult();
		} catch (Exception e) {
			System.out.println("\n\n"+e+"\n\n");
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * public static Results findByIdAndType(Long id, String type) { if (id ==
	 * null) return null; try { // TODO optimize return (Results) JPA.em()
	 * .createQuery("Results r where r.id = ? and r.type = ?") .setParameter(1,
	 * id).setParameter(2, type) .getSingleResult(); } catch (Exception e) {
	 * return null; } }
	 */
}