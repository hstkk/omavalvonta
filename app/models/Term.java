package models;

import java.util.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import models.helpers.Crud;
import models.helpers.UserModel;

import org.hibernate.envers.Audited;

import play.db.jpa.*;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
@Entity(name = "Term")
@Audited
public class Term extends UserModel {

	public final static Crud<Term, Long> crud = new Crud<Term, Long>(Term.class);

	@Column(name = "name")
	// @Required
	// @NotNull
	public String name;

	@Column(name = "category")
	// @Required
	// @NotNull
	public int category;

	@Transient
	public TermCategory categoryEnum;

	@PrePersist
	private void enumToInt() {
		if (categoryEnum != null)
			category = categoryEnum.getValue();
	}

	@PostLoad
	private void intToEnum() {
		categoryEnum = TermCategory.setValue(this.category);
	}

	public String toString() {
		return name;
	}

	public static Map<String, String> options(TermCategory categoryEnum) {
		CriteriaBuilder criteriaBuilder = crud.getCriteriaBuilder();
		CriteriaQuery<Term> query = criteriaBuilder.createQuery(Term.class);
		Root<Term> root = query.from(Term.class);
		query.where(criteriaBuilder.equal(root.get(Term_.category),
				categoryEnum));
		return crud.options(query);
	}

	public static List<Term> findByCategory(TermCategory categoryEnum) {
		try {
			if (categoryEnum != null) {
				List<Term> terms = JPA
						.em()
						.createQuery(
								"from Term where category=? order by name ")
						.setParameter(1, categoryEnum.getValue())
						.getResultList();
				return terms;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}