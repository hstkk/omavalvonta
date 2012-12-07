package models;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import models.helpers.Crud;
import models.helpers.Model;
import models.helpers.Page;

import org.hibernate.envers.Audited;

import play.Play;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
@Entity(name = "FinalProduct")
@Audited
public class FinalProduct extends Model {

	public final static Crud<FinalProduct, Long> crud = new Crud<>(
			FinalProduct.class);

	@Required
	@NotNull
	public Date date = new Date();

	@ManyToOne(cascade = CascadeType.ALL)
	@Required
	@NotNull
	public Term destiny;

	@Required
	@NotNull
	public Double amount;

	@ManyToOne(cascade = CascadeType.ALL)
	@Required
	@NotNull
	public Term unit;

	@ManyToOne(cascade = CascadeType.ALL)
	@Required
	@NotNull
	public Batch batch;

	@Lob
	public String comment;

	public static FinalProduct findByBatch(Batch batch) {
		try {
			if (batch != null) {
				Long id = batch.id;
				return (FinalProduct) JPA
						.em()
						.createQuery(
								"from FinalProduct " + "where batch.id = ?")
						.setParameter(1, id).getSingleResult();
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static Page<FinalProduct> page(int index) {
		try {
			int size = Play.application().configuration().getInt("page.size");
			if (index < 1)
				index = 1;
			int rows = (int) JPA.em()
					.createQuery("select count(*) from FinalProduct")
					.getSingleResult();
			List<FinalProduct> list = JPA.em().createQuery("from FinalProduct")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
			if (rows > 0 && list != null && !list.isEmpty())
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return null;
	}
}