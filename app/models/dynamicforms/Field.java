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

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("Tietotyypiltä ");
		stringBuilder.append(type.toString());
		if (isRequired)
			stringBuilder.append(", vaadittu");
		if (isSigned)
			stringBuilder.append(", kuitattava");
		if (type == FieldType.INT || type == FieldType.DOUBLE) {
			if (min != null)
				stringBuilder.append(", minimi " + min);
			if (max != null)
				stringBuilder.append(", maksimi " + max);
		}
		return stringBuilder.toString();
	}

	public void set() {
		if (this.form.id == null)
			this.form = null;
		else
			this.form = Form.findById(this.form.id);
	}

	public boolean save() {
		try {
			set();
			JPA.em().persist(this);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update() {
		try {
			set();
			JPA.em().merge(this);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Finds field by field id.
	 * 
	 * @param Fields
	 *            id.
	 * @return Field object if find is successful else null.
	 */
	public static Field findById(Long id) {
		if (id == null)
			return null;
		try {
			return JPA.em().find(Field.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	// TODO or basedon group by form in one query
	@SuppressWarnings("unchecked")
	public static List<Field> findByFormAndBasedOn(Form form) {
		if (form == null)
			return null;
		List<Field> fields = null;
		try {
			fields = JPA.em().createQuery("from Field f where f.form = ?")
					.setParameter(1, form).getResultList();
		} catch (Exception e) {
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

	@SuppressWarnings("unchecked")
	public static List<Field> findByForm(Form form) {
		if (form == null)
			return null;
		try {
			List<Field> fields = JPA.em()
					.createQuery("from Field f where f.form = ?")
					.setParameter(1, form).getResultList();
			return fields;
		} catch (Exception e) {
		}
		return null;
	}

	public static Field findByFormAndId(Form form, Long id) {
		if (form != null && id != null) {
			try {
				return (Field) JPA
						.em()
						.createQuery(
								"from Field f where f.form = ? and f.id = ?")
						.setParameter(1, form).setParameter(2, id)
						.getSingleResult();
			} catch (Exception e) {
			}
		}
		return null;
	}
}