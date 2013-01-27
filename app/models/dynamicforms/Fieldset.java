package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import models.helpers.Crud;
import models.helpers.UserModel;
import utils.*;

import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Fieldset extends UserModel {

	public Fieldset() {
	}

	public final static Crud<Fieldset, Long> crud = new Crud<Fieldset, Long>(Fieldset.class);

	@Required
	@NotNull
	public String name;

	@Lob
	public String description;

	@Required
	@NotNull
	public boolean isActive;

	public String toString() {
		return name;
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