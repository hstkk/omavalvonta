package models;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import models.helpers.JpaModel;
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
@Entity
@Audited
public class Unit extends JpaModel {
	@Required
	@NotNull
	@Column(unique = true)
	public String name;

	public String toString() {
		return name;
	}

	public static Unit findById(Long id) {
		try {
			if (id != null)
				return JPA.em().find(Unit.class, id);
		} catch (Exception e) {
		}
		return null;
	}

	public static Page page(int index) {
		try {
			int size = Play.application().configuration().getInt("page.size");
			if (index < 1)
				index = 1;
			Long rows = (Long) JPA.em()
					.createQuery("select count(*) from Unit").getSingleResult();
			List<Unit> list = JPA.em()
					.createQuery("from Unit order by name asc")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
			if (rows != null || list != null)
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> options() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		try {
			List<Unit> units = JPA.em()
					.createQuery("from Unit order by name").getResultList();
			for (Unit unit : units)
				map.put(unit.id.toString(), unit.toString());
			return map;
		} catch (Exception e) {
			return map;
		}
	}
}