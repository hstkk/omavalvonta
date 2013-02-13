package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import models.helpers.Crud;
import models.helpers.UserModel;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import play.data.validation.Constraints.Required;
import play.db.jpa.JPA;
import utils.Converter;
import utils.Helper;
import forms.Ingredients;

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

	// TODO ingredientAmounts
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

	// TODO JPQL to CriteriaBuilder?
	@SuppressWarnings("unchecked")
	public static List<Batch> findByIngredientSupply(
			IngredientSupply ingredientSupply) {
		try {
			if (ingredientSupply != null) {
				List<Batch> list = JPA
						.em()
						.createQuery(
								"select b from Batch b join b.ingredientAmounts as IngredientSupply where IngredientSupply = ?")
						.setParameter(1, ingredientSupply).getResultList();
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public FinalProduct getFinalProduct() {
		return FinalProduct.findByBatch(this);
	}
}