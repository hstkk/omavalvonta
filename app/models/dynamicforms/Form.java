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
import org.hibernate.Hibernate;

@Entity
// TODO field order
public class Form extends JpaModel {

	// @Valid
	@ManyToOne
	public Form basedOn = null;

	@Required
	@NotNull
	public String name;

	@Lob
	public String description;

	@Required
	public Boolean isActive = false;

	public Form() {
	}

	public String toString() {
		return name;
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

	@SuppressWarnings("unchecked")
	public static List<Form> findAll() {
		try {
			return JPA.em().createQuery("select f.id, f.name from Form f")
					.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static HashMap<String, String> options() {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			List<Form> forms = JPA.em().createQuery("select f.id, f.name from Form f")
					.getResultList();
			for(Form form: forms)
				map.put(form.id.toString(), form.toString());
			return map;
		} catch (Exception e) {
			System.out.print("\n\n"+e+"\n\n");
			return map;
		}
	}
}
