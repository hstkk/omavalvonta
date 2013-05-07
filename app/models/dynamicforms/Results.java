package models.dynamicforms;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.envers.Audited;
import play.data.validation.Constraints.Required;
import models.Batch;
import models.helpers.Dao;
import models.helpers.Model;

@Entity
@Audited
public class Results extends Model {
	public final static Dao<Results, Long> dao = new Dao<Results, Long>(
			Results.class);

	@Required
	@ManyToOne(cascade = CascadeType.ALL)
	@NotNull
	@Valid
	public Form form;

	@Required
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(updatable = false)
	@NotNull
	@Valid
	public List<Batch> batches = new ArrayList<Batch>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "results_id", nullable = false)
	@Valid
	@NotNull
	public List<Result> results = new ArrayList<Result>();

	public static play.data.Form<Results> getEmptyForm() {
		play.data.Form<Results> form = new play.data.Form<Results>(
				Results.class);
		return form;
	}

	public play.data.Form<Results> getPrefilledForm() {
		Results results = this;
		for (Fieldset fieldset : results.form.fieldsets) {
			for (Field field : fieldset.fields) {
				Result result = new Result();
				result.field = field;
				results.results.add(result);
			}
		}
		play.data.Form<Results> form = new play.data.Form<Results>(
				Results.class);
		return form.fill(results);
	}

	@Transient
	private Map<String, Field> fieldMap;

	public Field getField(String key) {
		if (fieldMap == null) {
			fieldMap = new LinkedHashMap<String, Field>();
			if (form.fieldsets != null)
				for (Fieldset fieldset : form.fieldsets)
					if (fieldset.fields != null)
						for (Field field : fieldset.fields)
							fieldMap.put(field.id.toString(), field);
		}
		return fieldMap.get(key);
	}

	@Transient
	private Map<String, Fieldset> fieldsetMap;

	public Fieldset getFieldset(String key) {
		if (fieldsetMap == null) {
			fieldsetMap = new LinkedHashMap<String, Fieldset>();
			if (form != null && form.fieldsets != null)
				for (Fieldset fieldset : form.fieldsets)
					fieldsetMap.put(fieldset.id.toString(), fieldset);
		}
		return fieldsetMap.get(key);
	}

	@Transient
	private List<String> printedFieldsets = new ArrayList<String>();

	public boolean isFieldsetPrinted(String key) {
		boolean printed = printedFieldsets.contains(key);
		if (!printed)
			printedFieldsets.add(key);
		return printed;
	}
}
