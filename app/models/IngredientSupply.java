package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Target;

import models.dynamicforms.Form;
import models.helpers.JpaModel;
import models.helpers.KeyValue;
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

	@Required
	@NotNull
	@Formats.DateTime(pattern = "dd.MM.yyyy")
	public Date received;

	// TODO bestbefore
	//@Required
	//@NotNull
	@Formats.DateTime(pattern = "dd.MM.yyyy")
	public Date bestBefore;

	@Required
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	public Term unit;

	private void set() {
		if (this.ingredient.id == null)
			this.ingredient = null;
		else
			this.ingredient = Ingredient.findById(this.ingredient.id);
		if (this.unit.id == null)
			this.unit = null;
		else
			this.unit = Term.crud.read(this.unit.id);
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

	public static KeyValue<String, Integer> findAliveByIngredient(Ingredient ingredient, int index) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			if (ingredient != null) {
				List<IngredientSupply> list = JPA
						.em()
						.createQuery(
								"from IngredientSupply i where i.amountAvailable > 0 and i.ingredient.id = ? and current_date() < i.bestBefore order by i.received asc ")
						.setParameter(1, ingredient.id).getResultList();
				if (!list.isEmpty()) {
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
							"dd.MM.yyyy");
					stringBuilder.append("<fieldset>");
					stringBuilder.append("<legend>");
					stringBuilder.append(ingredient.toString());
					stringBuilder.append("</legend>");
					for (IngredientSupply i : list) {
						stringBuilder
								.append("<div><div class=\"input-append\">");
						stringBuilder.append("<input type=\"hidden\" name=\"values[");
						stringBuilder.append(index);
						stringBuilder.append("].id\" value=\"");
						stringBuilder.append(i.id);
						stringBuilder.append("\"/>");
						stringBuilder
								.append("<input class=\"span2\" name=\"values[");
						stringBuilder.append(index);
						stringBuilder
								.append("].amount\" size=\"16\" type=\"text\" placeholder=\"");
						stringBuilder.append(i.amountAvailable);
						stringBuilder.append(" ");
						stringBuilder.append(i.unit);
						stringBuilder.append("\" max=\"");
						stringBuilder.append(i.amountAvailable);
						stringBuilder.append("\"><span class=\"add-on\">");
						stringBuilder.append(i.unit);
						stringBuilder.append("</span>");
						stringBuilder.append("</div>");
						stringBuilder.append("<span class=\"help-inline\">");
						stringBuilder.append("Vastaanottopäivä ");
						stringBuilder.append(simpleDateFormat
								.format(i.received));
						stringBuilder.append(". Parasta ennen ");
						stringBuilder.append(simpleDateFormat
								.format(i.bestBefore));
						stringBuilder.append(".</span></div><br/>");
						index++;
					}
					stringBuilder.append("</fieldset>");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new KeyValue<String, Integer>(stringBuilder.toString(), index);
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
			int rows = (int) JPA.em()
					.createQuery("select count(*) from IngredientSupply")
					.getSingleResult();
			List<IngredientSupply> list = JPA
					.em()
					.createQuery(
							"from IngredientSupply i order by i.received asc")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
			if (rows > 0 && list != null && !list.isEmpty())
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return null;
	}
}