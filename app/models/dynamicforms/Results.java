package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import models.Batch;
import models.helpers.JpaModel;

import forms.Product;
import forms.dynamicforms.Fieldset;

import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
public class Results extends JpaModel {
	@Required
	@NotNull
	public Date updated;

	@Required
	@ManyToOne(cascade = CascadeType.ALL)
	@NotNull
	public Form form;

	@Required
	@ManyToOne(cascade = CascadeType.ALL)
	@NotNull
	public Product product;

	@Required
	public Boolean isDone = false;

	@Required
	// @Valid
	@OneToMany(cascade = CascadeType.ALL)
	public List<Result> results;

	public Results() {
	}

	public Results(Product product, List<Fieldset> values, Form form) {
		this.product = product;
		this.updated = new Date();
		this.form = form;
		this.results = new ArrayList<Result>();
		System.out.println(values.size());
		for (Fieldset value : values)
			results.add(new Result(value));
	}

	public static Results findById(int id) {
		return JPA.em().find(Results.class, id);
	}

	public static boolean getIsDone(Long batchId, Long formId) {
		try {
			if (batchId != null && formId != null)
				return ((Results) JPA
						.em()
						.createQuery(
								"from Results r where r.batch.id = ? and r.form.id = ?")
						.setParameter(1, batchId).setParameter(2, formId)
						.getSingleResult()).isDone;
		} catch (Exception e) {
		}
		return false;
	}
}