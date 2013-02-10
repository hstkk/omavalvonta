package models;

import java.util.*;

import javax.persistence.*;

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
	//@Required
	//@NotNull
	public String name;

	@Column(name = "category")
	//@Required
	//@NotNull
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

	@SuppressWarnings("unchecked")
	public static Map<String, String> options(TermCategory categoryEnum) {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		try {
			if (categoryEnum != null) {
				List<Term> terms = JPA
						.em()
						.createQuery(
								"from Term where category=? order by name ")
						.setParameter(1, categoryEnum.getValue())
						.getResultList();
				for (Term term : terms)
					map.put(term.id.toString(), term.toString());
			}
		} catch (Exception e) {
		}
		return map;
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