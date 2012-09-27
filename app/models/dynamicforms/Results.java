package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import models.Batch;
import models.helpers.JpaModel;
import models.helpers.Page;

import forms.Product;
import forms.dynamicforms.Dynamic;
import forms.dynamicforms.Fieldset;

import play.Play;
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
	public models.Product product;

	@Required
	public Boolean isDone = false;

	@Required
	// @Valid
	// @Column(name = "result")
	@JoinTable(name = "ResultsResult", joinColumns = { @JoinColumn(name = "results_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "result_id", referencedColumnName = "id", unique = true) })
	@OneToMany(cascade = CascadeType.ALL)
	public List<Result> results;

	public Results() {
	}

	public Results(models.Product product, Dynamic dynamic, Form form) {
		this.id = dynamic.id;
		this.product = product;
		this.updated = new Date();
		this.form = form;
		this.results = new ArrayList<Result>();
		for (Fieldset value : dynamic.values)
			results.add(new Result(value));
	}

	public static Results findById(Long id) {
		try {
			if (id != null)
				return JPA.em().find(Results.class, id);
		} catch (Exception e) {
		}
		return null;
	}

	public Map<Field, List<Result>> getHistory() {
		Map<Field, List<Result>> history = new LinkedHashMap<Field, List<Result>>();
		for (Result result : results)
			history.put(result.field, result.getHistory());
		return history;
	}

	public static Page page(int index) {
		try {
			int size = Play.application().configuration().getInt("page.size");
			if (index < 1)
				index = 1;
			Long rows = (Long) JPA.em()
					.createQuery("select count(*) from Results")
					.getSingleResult();
			List<Results> list = JPA.em()
					.createQuery("from Results order by id desc")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
			if (rows != null && list != null && !list.isEmpty())
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return null;
	}

}