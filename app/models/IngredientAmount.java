package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import models.dynamicforms.Form;
import models.helpers.JpaModel;
import models.helpers.Page;

import play.Play;
import play.data.format.*;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;
import play.db.jpa.JPA;

@Entity
@Audited
public class IngredientAmount extends JpaModel {

	@Required
	@NotNull
	@ManyToMany(cascade = CascadeType.ALL)
	public IngredientSupply ingredientSupply;

	@Required
	@NotNull
	@Min(0)
	public Double amount;

	private void set() {
		if (this.ingredientSupply.id == null)
			this.ingredientSupply = null;
		else
			this.ingredientSupply = IngredientSupply
					.findById(this.ingredientSupply.id);
	}

	public boolean save() {
		try {
			set();
			JPA.em().persist(this);
			return true;
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}

	public static List<IngredientAmount> findByIngredientSupply(IngredientSupply ingredientSupply) {
		try {
			if (ingredientSupply != null) {
				List<IngredientAmount> list = JPA
						.em()
						.createQuery(
								"from IngredientAmount i where i.ingredientSupply.id = ? order by i.id asc")
						.setParameter(1, ingredientSupply.id).getResultList();
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}