package models;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import models.dynamicforms.Form;
import models.dynamicforms.Results;
import models.helpers.Crud;
import models.helpers.JpaModel;
import models.helpers.Page;
import models.helpers.UserModel;

import org.hibernate.annotations.Target;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import forms.Ingredients;

import play.Play;
import play.data.validation.Constraints.*;
import play.db.jpa.*;
import utils.Converter;
import utils.Helper;

@Entity
@Audited
public class Batch extends UserModel {
	public final static Crud<Batch, Long> crud = new Crud<Batch, Long>(
			Batch.class);

	@Required
	@NotNull
	public Date created = new Date();

	@Required
	@ManyToOne(cascade = CascadeType.ALL)
	public Product product;

	@Required
	public Boolean isDone = false;

	@ManyToMany(cascade = CascadeType.ALL)
	@NotAudited
	public List<IngredientAmount> ingredientAmounts = new ArrayList<IngredientAmount>();

	public Batch() {
	}

	// TODO JPA multisave?
	public Batch(forms.Batch form) {
		this.product = form.product;
		for (Ingredients i : form.values)
			if (i.amount != null) {
				IngredientSupply ingredientSupply = IngredientSupply.crud
						.findById(i.id);
				if (ingredientSupply != null)
					ingredientAmounts.add(new IngredientAmount(
							ingredientSupply, i.amount));
			}
	}

	@Override
	@PrePersist
	public void onCreate() {
		super.onCreate();
		if (this.product.id == null)
			this.product = null;
		else
			this.product = Product.crud.getReference(this.product.id);
	}

	public String toString() {
		String separator = Helper.getOrElse("batch.separator");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Converter.dateToString(this.created,
				Helper.getOrElse("batch.date")));
		stringBuilder.append(separator);
		stringBuilder.append(this.product.id);
		stringBuilder.append(separator);
		stringBuilder.append(this.id);
		return stringBuilder.toString();
	}

	public static List<Batch> findByIngredientSupply(Long id) {
		CriteriaBuilder criteriaBuilder = crud.getCriteriaBuilder();
		CriteriaQuery<Batch> query = criteriaBuilder.createQuery(Batch.class);
		Root<Batch> root = query.from(Batch.class);
		Join<Batch, IngredientAmount> join = root.join(Batch_.ingredientAmounts);
		query.where(criteriaBuilder.equal(root.get(Batch_.ingredientAmounts),
				categoryEnum));
		return crud.findAllBy(query);
		/*try {
			if (id != null) {
				List<Batch> list = JPA
						.em()
						.createQuery(
								"select b from Batch b join b.ingredientAmounts as IngredientSupply where IngredientSupply.id = ?")
						.setParameter(1, id).getResultList();
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;*/
	}

	// TODO remove
	public static String checkboxify() {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			List<Batch> list = findNotDone();
			int i = 0;
			for (Batch batch : list) {
				stringBuilder.append("<label class=\"checkbox\">");
				stringBuilder
						.append("<input type=\"checkbox\" name=\"batchIds[");
				stringBuilder.append(i);
				stringBuilder.append("]\" value=\"");
				stringBuilder.append(batch.id);
				stringBuilder.append("\">");
				stringBuilder.append(batch.toString());
				stringBuilder.append("</label>");
				i++;
			}
		} catch (Exception e) {
		}
		return stringBuilder.toString();
	}
}