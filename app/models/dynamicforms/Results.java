package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import models.Batch;
import models.Term;
import models.Term_;
import models.helpers.Crud;
import models.helpers.JpaModel;
import models.helpers.Model;
import models.helpers.Page;

import forms.dynamicforms.Dynamic;
import forms.dynamicforms.Fieldset;

import play.Play;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
public class Results extends Model {

	public final static Crud<Results, Long> crud = new Crud<Results, Long>(
			Results.class);

	@Required
	@NotNull
	public Date created = new Date();

	@Required
	@ManyToOne(cascade = CascadeType.ALL)
	@NotNull
	public Form form;

	@Required
	@ManyToOne(cascade = CascadeType.ALL)
	@NotNull
	public models.Product product;

	@Required
	public Boolean isDone = false;

	@Required
	// @Valid
	// @Column(name = "result")
	@JoinTable(name = "ResultsResult", joinColumns = { @JoinColumn(name = "results_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "result_id", referencedColumnName = "id", unique = true) })
	@OneToMany(cascade = CascadeType.ALL)
	public List<Result> results;

	@Required
	@ManyToMany(cascade = CascadeType.ALL)
	@NotNull
	public List<Batch> baches;

	public Results() {
	}

	public Results(models.Product product, Dynamic dynamic, Form form) {
		Results old = Results.findById(dynamic.id);
		if (old != null)
			this.created = old.created;
		this.id = dynamic.id;
		this.product = product;
		this.form = form;
		this.baches = new ArrayList<Batch>();
		for (Long id : dynamic.batchIds)
			if (id != null)
				baches.add(Batch.findById(id));
		if (this.baches.isEmpty() && old != null)
			this.baches = old.baches;
		this.results = new ArrayList<Result>();
		for (Fieldset value : dynamic.values)
			results.add(new Result(value));
		int resultsIsDone = 0;
		for (Result result : results)
			if (result.isDone)
				resultsIsDone++;
		if (!results.isEmpty() && resultsIsDone == results.size())
			this.isDone = true;
	}

	public Map<Field, List<Result>> getHistory() {
		Map<Field, List<Result>> history = new LinkedHashMap<Field, List<Result>>();
		for (Result result : results)
			history.put(result.field, result.getHistory());
		return history;
	}

	// TODO JPQL to CriteriaBuilder?
	@SuppressWarnings("unchecked")
	public static List<Results> findByBatch(Batch batch) {
		try {
			if (batch != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(batch.created);
				List<Results> list = JPA
						.em()
						.createQuery(
								"select r from Results r "
										+ "where ? in elements (r.baches)")
						.setParameter(1, batch).getResultList();
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}