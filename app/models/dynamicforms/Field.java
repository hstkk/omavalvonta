package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.envers.Audited;

import models.TermCategory;
import models.helpers.JpaModel;

import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Field extends JpaModel {
	@Transient
	public FieldType fieldTypeEnum;

	@Required
	@NotNull
	public Integer fieldType;

	@Transient
	public When whenEnum; // aka määritys tiheys

	@Column(name="tiheys")
	public Integer when;

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

	@Required
	@NotNull
	public boolean isImportant;

	@ManyToOne(cascade = CascadeType.ALL)
	@Required
	@NotNull
	public Form form;

	public Double min;

	public Double max;

	public Boolean targetValue;

	@PrePersist
	private void enumToInt() {
		if (whenEnum != null)
			when = whenEnum.getValue();
		if (fieldTypeEnum != null)
			fieldType = fieldTypeEnum.getValue();
	}

	@PostLoad
	private void intToEnum() {
		if(when != null)
			whenEnum = When.setValue(this.when);
		if(fieldType != null)
			fieldTypeEnum = FieldType.setValue(this.fieldType);
	}

	public Field() {
	}

	public String toString() {
		if(fieldType != null)
			fieldTypeEnum = FieldType.setValue(this.fieldType);
		StringBuilder stringBuilder = new StringBuilder("Tietotyypiltä ");
		stringBuilder.append(fieldTypeEnum.toString());
		if (isRequired)
			stringBuilder.append(", vaadittu");
		if (isSigned)
			stringBuilder.append(", kuitattava");
		if (fieldTypeEnum == FieldType.INT || fieldTypeEnum == FieldType.DOUBLE) {
			if (min != null)
				stringBuilder.append(", minimi " + min);
			if (max != null)
				stringBuilder.append(", maksimi " + max);
		} else if (fieldTypeEnum == FieldType.CHECKBOX && targetValue != null) {
			stringBuilder.append(", tavoite tulos ");
			if (targetValue)
				stringBuilder.append("kyllä");
			else
				stringBuilder.append("ei");
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
		set();
		//if (form != null)
			form.update();
	}

	public String validate() {
		StringBuilder result = new StringBuilder();
		if (fieldTypeEnum != FieldType.INT && fieldTypeEnum != FieldType.DOUBLE) {
			if (min != null)
				result.append("Vain numeraalisilla arvoilla voi olla minimi. ");
			if (max != null)
				result.append("Vain numeraalisilla arvoilla voi olla maksimi. ");
		} else if (min != null && max != null && min >= max)
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
			Long id = form.id;
			List<Field> fields = JPA.em()
					.createQuery("from Field f where f.form.id = ?")
					.setParameter(1, id).getResultList();
			return fields;
		} catch (Exception e) {
			System.out.println("\n\n" + e + "\n\n");
			e.printStackTrace();
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

	public static List<Result> headerify(Form form) {
		List<Result> headers = new ArrayList<Result>();
		if (form != null && form.id != null)
			try {
				List<Field> legends = JPA
						.em()
						.createQuery(
								"from Field f where f.form = ? and f.fieldType = ?")
						.setParameter(1, form)
						.setParameter(2, FieldType.LEGEND.getValue()).getResultList();
				for (Field legend : legends)
					headers.add(new Result(legend));
			} catch (Exception e) {
			}
		return headers;
	}

	public static Map<String, String> targetOptions() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("true", "kyllä");
		map.put("false", "ei");
		return map;
	}
}