package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.envers.Audited;

import models.Product;
import models.Term;
import models.TermCategory;
import models.helpers.Crud;
import models.helpers.UserModel;
import utils.*;

import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Form extends UserModel {

	public Form() {
	}

	public final static Crud<Form, Long> crud = new Crud<Form, Long>(Form.class);

	@Required
	@NotNull
	public String name;

	@Lob
	public String description = "";

	@Required
	@NotNull
	public boolean isActive;

	@OneToMany(cascade = CascadeType.ALL)
	@IndexColumn(name = "position ", base = 1)
	public List<Fieldset> fieldsets = new ArrayList<Fieldset>();

	public String toString() {
		return name;
	}

	@PrePersist
	@PreUpdate
	private void onPre() {
		this.fieldsets = Fieldset.crud.getReference(this.fieldsets);
	}

	public static List<Form> findByType(Term category) {
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

	// TODO Remove
	public static String checkboxes(String id) {
		StringBuilder stringBuilder = new StringBuilder();
		List<Form> checked = null;
		try {
			checked = Product.crud.findById(Long.parseLong(id)).forms;
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