package models;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import models.helpers.Dao;
import models.helpers.UserModel;

import org.hibernate.envers.Audited;

import play.data.validation.Constraints.Required;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
@Entity(name = "Term")
@Audited
public class Term extends UserModel {

	public final static Dao<Term, Long> dao = new Dao<Term, Long>(Term.class);

	@Column(name = "name")
	@Required
	@NotNull
	public String name;

	@Required
	@NotNull
	@Column(name = "category", nullable = false)
	public int category;

	@Transient
	public TermCategory categoryEnum;

	public String toString() {
		return name;
	}

	private void enumToInt() {
		if (categoryEnum != null)
			category = categoryEnum.getValue();
	}

	@Override
	public boolean onCreate() {
		return super.onCreate();
	}

	@Override
	public boolean onUpdate() {
		return super.onUpdate();
	}

	@PostLoad
	private void intToEnum() {
		categoryEnum = TermCategory.setValue(category);
	}

	public static List<Term> findByCategory(TermCategory categoryEnum) {
		return dao.findAllBy(getByCategory(categoryEnum));
	}

	private static CriteriaQuery<Term> getByCategory(TermCategory categoryEnum) {
		CriteriaBuilder criteriaBuilder = dao.getCriteriaBuilder();
		CriteriaQuery<Term> query = criteriaBuilder.createQuery(Term.class);
		Root<Term> root = query.from(Term.class);
		query.where(criteriaBuilder.equal(root.get(Term_.category),
				categoryEnum.getValue()));
		return query;
	}

	public static Map<String, String> options(TermCategory categoryEnum) {
		return dao.options(getByCategory(categoryEnum));
	}
}