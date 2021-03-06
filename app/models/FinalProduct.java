package models;

import java.util.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
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
	@NotNull(message = "", groups = { All.class, Partial.class })
	@Formats.DateTime(pattern = "dd.MM.yyyy")
	public Date date;

	@ManyToOne(cascade = CascadeType.ALL)
	@Required(groups = { All.class, Partial.class })
	@NotNull(message = "", groups = { All.class, Partial.class })
	@Valid
	public Term destiny;

	@Required(groups = { All.class, Partial.class })
	@NotNull(message = "", groups = { All.class, Partial.class })
	@LocalizedDouble
	public Double amount;

	@ManyToOne(cascade = CascadeType.ALL)
	@Required(groups = { All.class, Partial.class })
	@NotNull(message = "", groups = { All.class, Partial.class })
	@Valid
	public Term unit;

	@OneToOne(cascade = CascadeType.ALL)
	@Required(groups = { All.class })
	@NotNull(message = "", groups = { All.class })
	@Valid
	public Batch batch;

	@Lob
	public String comment;

	@Override
	public String toString() {
		return destiny.toString();
	}

	private void set() {
		this.unit = Term.dao.getReference(this.unit);
		this.destiny = Term.dao.getReference(this.destiny);
	}

	@Override
	public boolean onCreate() {
		set();
		return super.onCreate();
	}

	@Override
	public boolean onUpdate() {
		set();
		return super.onUpdate();
	}

	public static FinalProduct findByBatch(Batch batch) {
		CriteriaBuilder criteriaBuilder = dao.getCriteriaBuilder();
		CriteriaQuery<FinalProduct> query = criteriaBuilder
				.createQuery(FinalProduct.class);
		Root<FinalProduct> root = query.from(FinalProduct.class);
		Join<FinalProduct, Batch> join = root.join(FinalProduct_.batch);
		query.where(criteriaBuilder.equal(join.get(Batch_.id), batch.id));
		return dao.findBy(query);
	}

	public static List<FinalProduct> findByUser(User user) {
		if (user == null)
			return null;
		CriteriaBuilder criteriaBuilder = dao.getCriteriaBuilder();
		CriteriaQuery<FinalProduct> query = criteriaBuilder
				.createQuery(FinalProduct.class);
		Root<FinalProduct> root = query.from(FinalProduct.class);
		Join<FinalProduct, User> join = root.join(FinalProduct_.user);
		query.where(criteriaBuilder.equal(join.get(User_.id), user.id));
		return dao.findAllBy(query);
	}

	public static boolean isFinished(List<Batch> batches) {
		if (batches != null)
			for (Batch batch : batches)
				if (batch.finalProduct != null
						&& batch.finalProduct.destiny != null)
					return true;
		return false;
	}
}