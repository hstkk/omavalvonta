package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;

import models.Ingredient;
import models.Product;
import models.Term;
import models.TermCategory;
import models.helpers.Crud;
import models.helpers.JpaModel;
import models.helpers.Model;
import models.helpers.Page;
import utils.*;

import play.Play;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Form extends Model<Form> {

	public Form() {
		super(Form.class, "Form");
	}

	public final static Crud<Form, Long> crud = new Crud<Form, Long>(
			Form.class, "Form");

	@Required
	@NotNull
	public String name;

	@Lob
	public String description = "";

	// @Required
	@ManyToOne(cascade = CascadeType.ALL)
	public Term category;

	@Required
	@NotNull
	public boolean isActive;

	@ManyToMany(cascade = CascadeType.ALL)
	public List<Fieldset> fieldsets = new ArrayList<Fieldset>();

	public String toString() {
		return name;
	}

	@PrePersist
	private void prePersist() {
		if (this.category.id == null)
			this.category = null;
		else
			this.category = Term.crud.findById(this.category.id);
	}

	public static List<Form> findByTerm(Term category) {
		try {
			Long i = category.id;
			System.out.println("\n\n" + category.id + "\n\n");
			List<Form> list = JPA
					.em()
					.createQuery(
							"from Form f where f.category.id=? order by name")
					.setParameter(1, i).getResultList();
			return list;
		} catch (Exception e) {
			System.out.println("\n\n" + e + "\n\n");
			e.printStackTrace();
		}
		return null;
	}

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
		} catch (Exception e) {
		}
		return map;
	}

	public static Map<String, String> options() {
		return options(null);
	}

	public static String checkboxes(String id) {
		StringBuilder stringBuilder = new StringBuilder();
		List<Form> checked = null;
		try {
			checked = Product.findById(Long.parseLong(id)).forms;
		} catch (Exception e) {
		}
		try {
			List<Term> terms = Term.findByCategory(TermCategory.FORMTYPE);
			if (terms == null)
				return "";
			int i = 0;
			for (Term category : terms) {
				stringBuilder.append("<h5>");
				stringBuilder.append(category.name);
				stringBuilder.append("</h5>");
				List<Form> forms = findByTerm(category);
				for (Form form : forms) {
					stringBuilder.append("<label class=\"checkbox\">");
					stringBuilder
							.append("<input type=\"checkbox\" name=\"formIds[");
					stringBuilder.append(i);
					stringBuilder.append("]\" value=\"");
					stringBuilder.append(form.id);
					stringBuilder.append("\"");
					if (checked != null && checked.contains(form))
						stringBuilder.append(" checked=\"checked\"");
					stringBuilder.append(">");
					stringBuilder.append(form.toString());
					stringBuilder.append("</label>");
					i++;
				}
			}
		} catch (Exception e) {
		}
		return stringBuilder.toString();
	}
}