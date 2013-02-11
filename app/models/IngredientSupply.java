package models;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import models.helpers.Crud;
import models.helpers.UserModel;

import play.data.format.Formats;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;
import utils.Converter;

@Entity
public class IngredientSupply extends UserModel {
	public final static Crud<IngredientSupply, Long> crud = new Crud<IngredientSupply, Long>(IngredientSupply.class);

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

	@Required
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	public Term unit;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(ingredient);
		sb.append(" ");
		sb.append(amount);
		sb.append(" ");
		sb.append(unit);
		sb.append(" (");
		sb.append(Converter.dateToString(received));
		sb.append(")");
		return sb.toString();
	}

	@Override
	@PrePersist
	public void onCreate() {
		super.onCreate();

		if (this.ingredient.id == null)
			this.ingredient = null;
		else
			this.ingredient = Ingredient.crud.getReference(this.ingredient.id);
		if (this.unit.id == null)
			this.unit = null;
		else
			this.unit = Term.crud.getReference(this.unit.id);
	}

	public Date getBestBefore() {
		Calendar bestBefore = Calendar.getInstance();
		bestBefore.setTime(this.received);
		bestBefore.add(Calendar.DATE, this.ingredient.bestBefore);
		return bestBefore.getTime();
	}

	/*public static KeyValue<String, Integer> findAliveByIngredient(
			Ingredient ingredient, int index) {
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
						stringBuilder
								.append("<input type=\"hidden\" name=\"values[");
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
	}*/
}