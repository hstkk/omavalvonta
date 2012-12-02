package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;

import models.Ingredient;
import models.Product;
import models.Term;
import models.TermCategory;
import models.helpers.JpaModel;
import models.helpers.Page;
import utils.*;

import play.Play;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Fieldset extends JpaModel {

	@Required
	@NotNull
	public String name;

	@Required
	@ManyToMany(cascade = CascadeType.ALL)
	public List<Field> fields = new ArrayList<Field>();

	public Fieldset() {
	}

	public String toString() {
		return name;
	}

	@PrePersist
	public void formify() {
		this.html = utils.Form.formify(Field.findByFieldset(this));
	}

	public String toForm() {
		return this.html;
	}

	public static Fieldset findById(Long id) {
		try {
			if (id != null)
				return JPA.em().find(Fieldset.class, id);
		} catch (Exception e) {
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> options(String fieldsetId) {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		try {
			Long id = Converter.stringToLong(fieldsetId);
			List<Fieldset> fieldsets;
			if (id == null)
				fieldsets = JPA.em().createQuery("from Fieldset order by name")
						.getResultList();
			else
				fieldsets = JPA
						.em()
						.createQuery(
								"from Fieldset f where f.id != ? order by name")
						.setParameter(1, id).getResultList();
			for (Fieldset fieldset : fieldsets)
				map.put(fieldset.id.toString(), fieldset.toString());
			return map;
		} catch (Exception e) {
			return map;
		}
	}

	public static Map<String, String> options() {
		return options(null);
	}

	public static List<Fieldset> findByForm(Form form) {
		if (form == null || form.id == null)
			return null;
		try {
			List<Fieldset> fields = JPA.em()
					.createQuery("from Fieldset f where f in elements(?)")
					.setParameter(1, form.fieldsets).getResultList();
			return fields;
		} catch (Exception e) {
			System.out.println("\n\n" + e + "\n\n");
			e.printStackTrace();
		}
		return null;
	}
}