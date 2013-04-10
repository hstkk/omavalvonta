package models;

import java.util.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import models.helpers.Dao;
import models.helpers.Page;
import models.helpers.UserModel;

import org.hibernate.envers.Audited;

import play.Play;
import play.data.format.Formats;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
@Entity(name = "FinalProduct")
@Audited
public class FinalProduct extends UserModel {
	public final static Dao<FinalProduct, Long> dao = new Dao<FinalProduct, Long>(
			FinalProduct.class);

	@Required
	@NotNull
	@Formats.DateTime(pattern = "dd.MM.yyyy")
	public Date date;

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

	@OneToOne(cascade = CascadeType.ALL)
	@Required
	@NotNull
	public Batch batch;

	@Lob
	public String comment;

	@Override
	public String toString() {
		return destiny.toString();
	}

	public static FinalProduct findByBatch(Batch batch) {
		CriteriaBuilder criteriaBuilder = dao.getCriteriaBuilder();
		CriteriaQuery<FinalProduct> query = criteriaBuilder
				.createQuery(FinalProduct.class);
		Root<FinalProduct> root = query.from(FinalProduct.class);
		query.where(criteriaBuilder.equal(root.get(FinalProduct_.batch), batch));
		return dao.findBy(query);
	}
}