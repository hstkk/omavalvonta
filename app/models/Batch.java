package models;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import models.dynamicforms.Form;
import models.dynamicforms.Results;
import models.helpers.JpaModel;
import models.helpers.Page;

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
public class Batch extends JpaModel {
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

	private void set() {
		if (this.product.id == null)
			this.product = null;
		else
			this.product = Product.crud.getReference(this.product.id);
	}

	// TODO remove
	public boolean save() {
		try {
			set();
			JPA.em().persist(this);
			return true;
		} catch (Exception e) {
			return false;
		}
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

	// TODO remove
	public String createdToString() {
		return new SimpleDateFormat("HH:mm dd.MM.yyyy").format(this.created)
				.toString();
	}

	// TODO remove
	public static Batch findById(Long id) {
		if (id == null)
			return null;
		try {
			return JPA.em().find(Batch.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	// TODO remove
	public static List<Batch> findByIngredientSupply(Long id) {
		try {
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
		return null;
	}

	// TODO remove
	public static Page page(int index) {
		try {
			int size = Play.application().configuration().getInt("page.size");
			if (index < 1)
				index = 1;
			long rows = (long) JPA.em()
					.createQuery("select count(*) from Batch")
					.getSingleResult();
			List<Batch> list = JPA.em()
					.createQuery("from Batch b order by b.created desc")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
			if (rows > 0 && list != null && !list.isEmpty())
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return new Page(index, 0, 0, null);
	}

	// TODO remove
	public static List<Batch> findNotDone() {
		try {
			List<Batch> list = JPA
					.em()
					.createQuery(
							"from Batch b where b.isReady = false order by created")
					.getResultList();
			return list;
		} catch (Exception e) {
		}
		return null;
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