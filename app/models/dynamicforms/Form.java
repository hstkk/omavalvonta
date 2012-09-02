package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;

import models.helpers.JpaModel;
import models.helpers.Page;
import utils.*;

import play.Play;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Form extends JpaModel {

	// @Valid
	@ManyToOne(cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	public Form basedOn = null;

	@Required
	@NotNull
	public String name;

	@Lob
	public String description = "";

	@Lob
	@NotNull
	public String html = "";

	public Form() {
	}

	public String toString() {
		return name;
	}

	private void set() {
		try {
			if (this.basedOn.id == null)
				this.basedOn = null;
			else
				this.basedOn = Form.findById(this.basedOn.id);
		} catch (Exception e) {
		}
	}

	public boolean save() {
		try {
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
			formify();
			JPA.em().merge(this);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void formify() {
		String html = utils.Form.formify(Field.findByForm(this));
		if (html != null)
			this.html = html;
	}

	public String toForm() {
		if (this.basedOn == null || this.basedOn.html.equals(""))
			return this.html;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.basedOn.html);
		stringBuilder.append(this.html);
		return (stringBuilder.length() > 0) ? stringBuilder.toString() : null;
	}

	public static Form findById(Long id) {
		try {
			if (id != null)
				return JPA.em().find(Form.class, id);
		} catch (Exception e) {
		}
		return null;
	}

	public static Page page(int index) {
		try {
			int size = Play.application().configuration().getInt("page.size");
			if (index < 1)
				index = 1;
			Long rows = (Long) JPA.em()
					.createQuery("select count(*) from Form").getSingleResult();
			List<Form> list = JPA.em()
					.createQuery("from Form f order by f.name asc")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
			if (rows != null || list != null)
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return null;
	}

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

	public static Map<String, String> options() {
		return options(null);
	}
}