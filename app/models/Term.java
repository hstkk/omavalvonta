package models;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import models.helpers.Crud;
import models.helpers.Model;
import models.helpers.Page;

import org.hibernate.envers.Audited;

import play.Play;
import play.data.Form;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
@Entity(name = "Term")
@Audited
public class Term extends Model {

	public final static Crud<Term, Long> crud = new Crud<>(Term.class);

	@Column(name = "name")
	@Required
	@NotNull
	public String name;

	@Column(name = "category")
	@Required
	@NotNull
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

	public static Page<Term> page(int index) {
		try {
			int size = Play.application().configuration().getInt("page.size");
			if (index < 1)
				index = 1;
			long rows = (long) JPA.em()
					.createQuery("select count(*) from Term").getSingleResult();
			List<Term> list = JPA.em().createQuery("from Term order by name")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return new Page(index, 0, 0, null);
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