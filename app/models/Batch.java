package models;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import models.dynamicforms.Form;
import models.helpers.JpaModel;
import models.helpers.Page;

import org.hibernate.annotations.Target;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import play.Play;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Batch extends JpaModel {
	@Required
	@NotNull
	public Date created = new Date();

	@Required
	// @Valid
	@ManyToOne(cascade = CascadeType.ALL)
	public Product product;

	@Required
	public Boolean isReady = false;

	@ManyToMany(cascade = CascadeType.ALL)
	@NotAudited
	public List<IngredientAmount> ingredientAmounts = new ArrayList<IngredientAmount>();

	public Batch() {
	}

	public Batch(forms.Batch form) {
		this.product = form.product;
		Double[] amounts = (Double[]) form.amount.toArray();
		Long[] ids = (Long[]) form.ingredientSupplyId.toArray();
		for (int i = 0, max = ids.length; i < max; i++)
			if (amounts[i] != null) {
				IngredientSupply ingredientSupply = IngredientSupply
						.findById(ids[i]);
				if (ingredientSupply != null)
					ingredientAmounts.add(new IngredientAmount(
							ingredientSupply, amounts[i]));
			}
	}

	private void set() {
		if (this.product.id == null)
			this.product = null;
		else
			this.product = Product.findById(this.product.id);
	}

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
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Erä ");
		stringBuilder.append(this.id);
		stringBuilder.append(", ");
		stringBuilder.append(new SimpleDateFormat("HH:mm dd.MM.yyyy").format(
				this.created).toString());
		stringBuilder.append(", ");
		stringBuilder.append(product.name);
		return stringBuilder.toString();
	}

	public String createdToString() {
		return new SimpleDateFormat("HH:mm dd.MM.yyyy").format(this.created)
				.toString();
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

	public static List<Batch> findByIngredientSupply(Long id) {
		try {
			/*
			 * if (id != null) return JPA .em() .createQuery(
			 * "from Batch b where b.IngredientSupply.id = ? order by b.created asc"
			 * ) .setParameter(1, id).getResultList();
			 */
		} catch (Exception e) {
		}
		return null;
	}

	public static Page page(int index) {
		try {
			int size = Play.application().configuration().getInt("page.size");
			if (index < 1)
				index = 1;
			Long rows = (Long) JPA.em()
					.createQuery("select count(*) from Batch")
					.getSingleResult();
			List<Batch> list = JPA.em()
					.createQuery("from Batch b order by b.created asc")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
			if (rows != null && list != null && !list.isEmpty())
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return null;
	}

}