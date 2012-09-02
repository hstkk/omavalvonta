package models;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import models.helpers.JpaModel;
import models.helpers.Page;

import org.hibernate.annotations.Target;
import org.hibernate.envers.Audited;

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

/*	@Required
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@MapKey
	@Target(IngredientSupply.class)
	public Map<IngredientSupply, Double> ingredients = new HashMap<IngredientSupply, Double>();*/

	public Batch() {
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

	public boolean update() {
		try {
			set();
			JPA.em().merge(this);
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
			/*if (id != null)
				return JPA
						.em()
						.createQuery(
								"from Batch b where b.IngredientSupply.id = ? order by b.created asc")
						.setParameter(1, id).getResultList();*/
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
			if (rows != null || list != null)
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return null;
	}

}