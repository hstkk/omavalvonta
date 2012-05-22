package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.apache.commons.collections.ListUtils;
import org.hibernate.envers.Audited;

import models.JpaModel;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
public class Field extends JpaModel {
	@Required
	@Enumerated(EnumType.STRING)
	@NotNull
	public FieldType type;

	@Required
	@NotNull
	public String name;

	@Lob
	public String help;

	@Required
	@NotNull
	public boolean isRequired;

	@Required
	@NotNull
	public boolean isSigned;

	@ManyToOne
	@Required
	@NotNull
	public Form form;

	public Double min;

	public Double max;

	public Field() {
	}

	public Field(Form form, forms.dynamicforms.Field field) {
		this.form = form;
		this.name = field.name;
		this.help = field.help;
		this.type = field.type;
		this.isRequired = field.isRequired;
		this.isSigned = field.isSigned;
		this.min = field.min;
		this.max = field.max;
		this.save();
	}

	/**
	 * Finds field by field id.
	 * 
	 * @param Fields
	 *            id.
	 * @return Field object if find is successful else null.
	 */
	public static Field findById(Long id) {
		try {
			return JPA.em().find(Field.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	// TODO or basedon group by form in one query
	@SuppressWarnings("unchecked")
	public static List<Field> findByForm(Form form) {
		if (form == null)
			return null;
		List<Field> fields = null;
		try {
			fields = JPA.em().createQuery("from Field f where f.form = ?")
					.setParameter(1, form).getResultList();
		} catch (Exception e) {
			System.out.print(e.toString());
		}
		if (form.basedOn != null) {
			List<Field> inheritedFields = null;
			try {
				inheritedFields = JPA.em()
						.createQuery("from Field f where f.form = ?")
						.setParameter(1, form.basedOn).getResultList();
			} catch (Exception e) {
			}
			if (fields == null)
				return inheritedFields;
			else if (inheritedFields != null)
				return ListUtils.union(inheritedFields, fields);
		}
		return fields;
	}
}