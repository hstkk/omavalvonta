package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.collections.ListUtils;
import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.CascadeType;
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
	@OneToOne(orphanRemoval = true)
	// @Valid
	public Form basedOn;

	@Required
	@NotNull
	public String name;

	@Lob
	public String description;

	@Required
	public Boolean isActive;

	public Form() {
	}

	public Form(forms.dynamicforms.Manage manage) {
		set(manage);
	}

	public void set(forms.dynamicforms.Manage manage) {
		this.name = manage.name;
		this.description = manage.description;
		this.isActive = manage.isActive;
		if (manage.basedOn != null)
			this.basedOn = Form.findById(manage.basedOn);
	}

	public static Form findById(Long id) {
		try {
			return JPA.em().find(Form.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	public static Form findActiveById(Long id) {
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
}
