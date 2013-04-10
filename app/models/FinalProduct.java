package models;

import java.util.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import models.helpers.Dao;
import models.helpers.Page;
import models.helpers.UserModel;

import org.hibernate.envers.Audited;

import play.Play;
import play.data.format.Formats;
import play.data.validation.Constraints.*;
import play.db.jpa.*;
import utils.Formats.LocalizedDouble;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
@Entity(name = "FinalProduct")
@Audited
public class FinalProduct extends UserModel {
	public interface All {
	}

	public interface Partial {
	}

	public final static Dao<FinalProduct, Long> dao = new Dao<FinalProduct, Long>(
			FinalProduct.class);

	@Required(groups = { All.class, Partial.class })
	@NotNull(groups = { All.class, Partial.class })
	@Formats.DateTime(pattern = "dd.MM.yyyy")
	public Date date;

	@ManyToOne(cascade = CascadeType.ALL)
	@Required(groups = { All.class, Partial.class })
	@NotNull(groups = { All.class, Partial.class })
	@Valid
	public Term destiny;

	@Required(groups = { All.class, Partial.class })
	@NotNull(groups = { All.class, Partial.class })
	@LocalizedDouble
	public Double amount;

	@ManyToOne(cascade = CascadeType.ALL)
	@Required(groups = { All.class, Partial.class })
	@NotNull(groups = { All.class, Partial.class })
	@Valid
	public Term unit;

	@OneToOne(cascade = CascadeType.ALL)
	@Required(groups = { All.class })
	@NotNull(groups = { All.class })
	@Valid
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