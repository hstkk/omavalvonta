package models;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import models.helpers.Crud;
import models.helpers.JpaModel;
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
@Entity(name = "term")
@Audited
public class Term extends JpaModel {

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
		category = categoryEnum.getValue();
	}

	@PostLoad
	private void intToEnum() {
		categoryEnum = TermCategory.setValue(this.category);
	}

	public Term(forms.Term form) {
		set(form);
	}

	public void set(forms.Term form) {
		this.name = form.name;
	}

	public String toString() {
		return name;
	}

	public static List<Term> findByCategory(TermCategory categoryEnum) {
		try {
			if (categoryEnum != null) {
				List<Term> list = JPA
						.em()
						.createQuery(
								"from Term order by id asc where category=?")
						.setParameter(1, categoryEnum.getValue())
						.getResultList();
				return list;
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static Page<Term> page(int index, TermCategory categoryEnum) {
		try {
			if (categoryEnum != null) {
				int size = Play.application().configuration()
						.getInt("page.size");
				if (index < 1)
					index = 1;
				Long rows = (Long) JPA
						.em()
						.createQuery(
								"select count(*) from Term where category=?")
						.setParameter(1, categoryEnum.getValue())
						.getSingleResult();
				List<Term> list = JPA
						.em()
						.createQuery("from Term order by name where category=?")
						.setParameter(1, categoryEnum.getValue())
						.setFirstResult((index - 1) * size).setMaxResults(size)
						.getResultList();
				if (rows != null && list != null && !list.isEmpty())
					return new Page(index, size, rows, list);
			}
		} catch (Exception e) {
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> options() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		try {
			List<Term> units = JPA.em().createQuery("from Term order by name")
					.getResultList();
			for (Term unit : units)
				map.put(unit.id.toString(), unit.toString());
			return map;
		} catch (Exception e) {
			return map;
		}
	}
}