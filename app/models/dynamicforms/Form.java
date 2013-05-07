package models.dynamicforms;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import models.helpers.Dao;
import models.helpers.UserModel;
import org.hibernate.envers.Audited;
import play.data.validation.Constraints.*;

@Entity
@Audited
public class Form extends UserModel {
	public final static Dao<Form, Long> dao = new Dao<Form, Long>(Form.class);

	@Required
	@NotNull
	public String name;

	@Lob
	public String description;

	@ManyToMany
	@OrderColumn(name = "form_index")
	public List<Fieldset> fieldsets;

	public String toString() {
		return name;
	}

	@Override
	public void onCreate() {
		set();
		super.onCreate();
	}

	@Override
	public void onUpdate() {
		set();
		super.onUpdate();
	}

	private void set() {
		this.fieldsets = Fieldset.dao.getReference(this.fieldsets);
	}

	@Transient
	private Map<String, Field> fieldMap;

	public Field getField(String key) {
		if (fieldMap == null) {
			fieldMap = new LinkedHashMap<String, Field>();
			if (fieldsets != null)
				for (Fieldset fieldset : fieldsets)
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
			if (fieldsets != null)
				for (Fieldset fieldset : fieldsets)
					fieldsetMap.put(fieldset.id.toString(), fieldset);
		}
		return fieldsetMap.get(key);
	}

	public play.data.Form<Results> getForm() {
		Results results = new Results();
		results.form = this;
		for (Fieldset fieldset : fieldsets) {
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
	private List<String> printedFieldsets = new ArrayList<String>();

	public boolean isFieldsetPrinted(String key) {
		boolean printed = printedFieldsets.contains(key);
		if (!printed)
			printedFieldsets.add(key);
		return printed;
	}
}
