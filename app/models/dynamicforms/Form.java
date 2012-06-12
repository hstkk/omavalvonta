package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.collections.ListUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
//import org.hibernate.annotations.CascadeType;
import org.hibernate.envers.Audited;

import models.JpaModel;
import utils.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

import org.hibernate.Hibernate;

import antlr.Utils;

@Entity
@Audited
// TODO field order
// TODO fetch
public class Form extends JpaModel {

	// @Valid
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	public Form basedOn = null;

	@Required
	@NotNull
	public String name;

	@Lob
	public String description = "";

	@Lob
	public String html = "";

	@Required
	public Boolean isActive = false;

	public Form() {
	}

	public String toString() {
		return name;
	}

	private void set() {
		if (this.basedOn.id == null)
			this.basedOn = null;
		else
			this.basedOn = Form.findById(this.basedOn.id);
	}

	public boolean save() {
		try {
			System.out.println("KK");
			set();
			JPA.em().persist(this);
			return true;
		} catch (Exception e) {
			System.out.println(e);
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

	public void formify() {
		String html = utils.Form.formify(Field.findByForm(this));
		if (html != null)
			this.html = html;
		this.save();
	}

	public String toForm() {
		if (this.basedOn == null || this.basedOn.html.equals(""))
			return this.html;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.basedOn.toForm());
		stringBuilder.append(this.html);
		return (stringBuilder.length() > 0) ? stringBuilder.toString() : null;
	}

	public static Form findById(Long id) {
		if (id == null)
			return null;
		try {
			return JPA.em().find(Form.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	public static Form findActiveById(Long id) {
		if (id == null)
			return null;
		try {
			// TODO optimize: select f.id, f.basedOn, f.name, f.description from
			// Form f where f.isActive = true and f.id = ?
			return (Form) JPA.em()
					.createQuery("Form f where f.isActive = true and f.id = ?")
					.setParameter(1, id).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Form> findAll() {
		try {
			// TODO optimize: select f.id, f.name from Form f
			List<Form> forms = JPA.em().createQuery("from Form order by name")
					.getResultList();
			return forms;
		} catch (Exception e) {
			return null;
		}
	}

	// TODO optimize: select f.id, f.name from Form f
	@SuppressWarnings("unchecked")
	public static Map<String, String> options(String formId) {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		try {
			Long id = Converter.stringToLong(formId);
			List<Form> forms;
			if (id == null)
				forms = JPA.em().createQuery("from Form order by name")
						.getResultList();
			else
				forms = JPA
						.em()
						.createQuery(
								"from Form f where f.id != ? order by name")
						.setParameter(1, id).getResultList();
			for (Form form : forms)
				map.put(form.id.toString(), form.toString());
			return map;
		} catch (Exception e) {
			return map;
		}
	}
}
