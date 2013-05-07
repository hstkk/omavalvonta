package models.dynamicforms;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.envers.Audited;
import models.dynamicforms.Form;

@Entity
@Table(name = "Form_Fieldset")
@Audited
public class FormFieldset {
	@Id
	Long id;

	@JoinColumn(name = "form_id", updatable = false, nullable = false)
	@ManyToOne
	public Form form;

	@JoinColumn(name = "fieldset_id", updatable = false, nullable = false)
	@ManyToOne
	public Fieldset fieldset;

	@Column(name = "form_index", insertable = false, updatable = false)
	public Integer index;

	public FormFieldset(Form form, Fieldset fieldset) {
		this.form = form;
		this.fieldset = fieldset;
	}

	public static List<FormFieldset> prepare(Form form,
			List<FormFieldset> fieldsets) {
		List<FormFieldset> formFieldsets = new ArrayList<FormFieldset>();
		for (FormFieldset formFieldset : fieldsets) {
			Fieldset fieldset = Fieldset.dao
					.getReference(formFieldset.fieldset);
			if (fieldset != null)
				formFieldsets.add(new FormFieldset(form, fieldset));
		}
		return formFieldsets;
	}
}