package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.envers.Audited;

import models.Product;
import models.Term;
import models.TermCategory;
import models.Term_;
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

	// TODO Finetune vars
	public static Map<String, String> options(TermCategory categoryEnum) {
		CriteriaBuilder criteriaBuilder = crud.getCriteriaBuilder();
		CriteriaQuery<Form> query = criteriaBuilder.createQuery(Form.class);
		Root<Form> root = query.from(Form.class);
		query.where(criteriaBuilder.equal(root.get(Form_.category),
				categoryEnum));
		return crud.options(query);
	}
}