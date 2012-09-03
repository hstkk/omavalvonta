package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import models.dynamicforms.Form;
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
	public Double amountAvailable;

	@NotNull
	public Date received = new Date();

	@Required
	@NotNull
	@Formats.DateTime(pattern = "dd.MM.yyyy")
	public Date produced;

	// TODO bestbefore
	@Required
	@NotNull
	@Formats.DateTime(pattern = "dd.MM.yyyy")
	public Date bestBefore;

	@Required
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	public Unit unit;

	private void set() {
		if (this.ingredient.id == null)
			this.ingredient = null;
		else
			this.ingredient = Ingredient.findById(this.ingredient.id);
		if (this.unit.id == null)
			this.unit = null;
		else
			this.unit = Unit.findById(this.unit.id);
	}

	public boolean save() {
		try {
			this.amountAvailable = this.amount;
			set();
			JPA.em().persist(this);
			return true;
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}

	public boolean update() {
		try {
			set();
			JPA.em().merge(this);
			return true;
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}

	public static IngredientSupply findById(Long id) {
		try {
			if (id != null)
				return JPA.em().find(IngredientSupply.class, id);
		} catch (Exception e) {
		}
		return null;
	}

	public static String findAliveByIngredient(Ingredient ingredient) {
		try {
			if (ingredient != null) {
				List<IngredientSupply> list = JPA
						.em()
						.createQuery(
								"from IngredientSupply i where i.amountAvailable > 0 and i.ingredient.id = ? and current_date() < i.bestBefore order by i.produced asc ")
						.setParameter(1, ingredient.id).getResultList();
				if (!list.isEmpty()) {
					StringBuilder stringBuilder = new StringBuilder();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
							"dd.MM.yyyy");
					stringBuilder.append("<fieldset>");
					stringBuilder.append("<legend>");
					stringBuilder.append(ingredient.toString());
					stringBuilder.append("</legend>");
					for (IngredientSupply i : list) {
						stringBuilder.append("<div class=\"input-append\">");
						stringBuilder
								.append("<input class=\"span2\" id=\"appendedInput\" size=\"16\" type=\"text\"  placeholder=\"");
						stringBuilder.append(i.amountAvailable);
						stringBuilder.append(" ");
						stringBuilder.append(i.unit);
						stringBuilder.append("\"><span class=\"add-on\">");
						stringBuilder.append(i.unit);
						stringBuilder.append("</span>");
						stringBuilder.append("</div>");
						stringBuilder.append("<span class=\"help-inline\">");
						stringBuilder.append("Valmistuspäivä ");
						stringBuilder.append(simpleDateFormat
								.format(i.produced));
						stringBuilder.append(". Parasta ennen ");
						stringBuilder.append(simpleDateFormat
								.format(i.bestBefore));
						stringBuilder.append(".</span>");
					}
					stringBuilder.append("</fieldset>");
					return stringBuilder.toString();
				} else
					System.out.println("FAIL");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
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
			Long rows = (Long) JPA.em()
					.createQuery("select count(*) from IngredientSupply")
					.getSingleResult();
			List<IngredientSupply> list = JPA
					.em()
					.createQuery(
							"from IngredientSupply i order by i.produced asc")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
			if (rows != null && list != null && !list.isEmpty())
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return null;
	}
}