package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.collections.ListUtils;
import org.hibernate.envers.Audited;

import models.Content;
import models.JpaModel;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
// TODO field order
public class Form extends JpaModel {
	@OneToOne
	// @Valid
	public Form basedOn;

	@Required
	@NotNull
	public String name;

	@Lob
	public String description;

	// @Required
	// @Valid
	@OneToMany
	@JoinTable(name = "FormField", joinColumns = { @JoinColumn(name = "form_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "field_id", referencedColumnName = "id", unique = true) })
	public List<Field> fields = new ArrayList<Field>();

	@Required
	public Boolean isActive;

	public Form(forms.dynamicforms.Manage manage) {
		set(manage);
		this.save();
	}

	public void set(forms.dynamicforms.Manage manage) {
		this.name = manage.name;
		this.description = manage.description;
		this.isActive = manage.isActive;
		if (manage.basedOn != null)
			this.basedOn = Form.findById(manage.basedOn);
	}

	public void add(Field field) {
		if (field.id != null)
			fields.add(field);
	}

	public static Form findById(Long id) {
		try {
			return JPA.em().find(Form.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	public static Form findActiveById(int id) {
		try {
			return (Form) JPA
					.em()
					.createQuery(
							"select f.id, f.basedOn, f.name, f.description from Form f where f.isActive = true and f.id = ?")
					.setParameter(1, id).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Form> findAllActive() {
		try {
			return JPA
					.em()
					.createQuery(
							"select f.id, f.name from Form f where f.isActive = true")
					.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Field> findFields() {
		List<Field> fields = null;
		try {
			fields = JPA
					.em()
					.createQuery(
							"select f.id, f.name from Field f where f.id = ?")
					.setParameter(1, this.id).getResultList();
		} catch (Exception e) {
		}
		if (this.basedOn != null) {
			List<Field> inheritedFields = null;
			try {
				inheritedFields = JPA
						.em()
						.createQuery(
								"select * from Field inner join FormField ON Field.id = FormField.field_id where FormField.form_id = ?")
						.setParameter(1, this.id).getResultList();
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
