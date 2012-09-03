package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.envers.Audited;

import models.helpers.JpaModel;

import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Field extends JpaModel {
	@Required
	@Enumerated(EnumType.STRING)
	@NotNull
	public FieldType type;

	@Enumerated(EnumType.STRING)
	@Column(name = "tiheys")
	public When when; // aka määritys tiheys

	@Required
	@NotNull
	public String name; // aka määritys

	@Lob
	public String help; // aka vaatimus

	@Required
	@NotNull
	public boolean isRequired;

	@Required
	@NotNull
	public boolean isSigned;

	@ManyToOne(cascade = CascadeType.ALL)
	@Required
	@NotNull
	public Form form;

	public Double min;

	public Double max;

	public Double target;

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

	private void set() {
		if (this.form.id == null)
			this.form = null;
		else
			this.form = Form.findById(this.form.id);
	}

	public boolean save() {
		try {
			set();
			JPA.em().persist(this);
			formify();
			return true;
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}

	public boolean delete() {
		try {
			JPA.em().remove(this);
			formify();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update() {
		try {
			set();
			JPA.em().merge(this);
			formify();
			return true;
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}

	private void formify() {
		if (form != null)
			form.update();
	}

	public String validate() {
		StringBuilder result = new StringBuilder();
		if (type != FieldType.INT || type != FieldType.DOUBLE) {
			if (min != null)
				result.append("Vain numeraalisilla arvoilla voi olla minimi. ");
			if (max != null)
				result.append("Vain numeraalisilla arvoilla voi olla maksimi. ");
		} else if (min >= max)
			result.append("Minimi ei voi olla maksimia suurempi.");
		return result.length() > 0 ? result.toString() : null;
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
			if (id != null)
				return JPA.em().find(Field.class, id);
		} catch (Exception e) {
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static List<Field> findByForm(Form form) {
		if (form == null || form.id == null)
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