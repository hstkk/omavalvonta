package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import models.helpers.JpaModel;
import models.helpers.Page;

import play.Play;
import play.data.format.*;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;
import play.db.jpa.JPA;

@Entity
public class IngredientSupply extends JpaModel {

	@Required
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	public Ingredient ingredient;

	@Required
	@NotNull
	@Min(0)
	public Double amount;

	@Min(0)
	public Double amountAvailable = amount;

	@NotNull
	public Date received = new Date();

	@Required
	@NotNull
	@Formats.DateTime(pattern="dd.MM.yyyy")
	public Date produced;

	public static IngredientSupply findById(Long id) {
		try {
			if (id != null)
				return JPA.em().find(IngredientSupply.class, id);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Page.
	 * 
	 * @param index
	 *            the index
	 * @return the page
	 */
	public static Page page(int index) {
		try {
			int size = Play.application().configuration().getInt("page.size");
			if (index < 1)
				index = 1;
			Integer rows = (Integer) JPA.em()
					.createQuery("select count(*) from IngredientSupply")
					.getSingleResult();
			List<IngredientSupply> list = JPA.em()
					.createQuery("from IngredientSupply i order by i.produced asc")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
			if (rows != null || list != null)
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return null;
	}
}