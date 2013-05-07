package models.dynamicforms;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import models.helpers.Dao;
import models.helpers.UserModel;
import org.hibernate.envers.AuditMappedBy;
import org.hibernate.envers.Audited;
import play.data.validation.Constraints.*;

@Entity
@Audited
public class Fieldset extends UserModel {
	public final static Dao<Fieldset, Long> dao = new Dao<Fieldset, Long>(
			Fieldset.class);

	@Required
	@NotNull
	public String name;

	@Lob
	public String description;

	@OneToMany(cascade = CascadeType.ALL)
	@OrderColumn(name = "field_index")
	@JoinColumn(name = "fieldset_id", nullable = false)
	@AuditMappedBy(mappedBy = "fieldset", positionMappedBy = "index")
	@Valid
	public List<Field> fields = new ArrayList<Field>();

	public String toString() {
		return name;
	}

	public static play.data.Form<Form> getForm(Form form) {
		List<Fieldset> all = Fieldset.dao.findAll();
		if (form == null)
			form = new Form();
		for (Fieldset fieldset : form.fieldsets)
			all.remove(fieldset);
		form.fieldsets.addAll(all);
		play.data.Form<Form> FORM = new play.data.Form<Form>(Form.class);
		return FORM.fill(form);
	}

	public static Map<String, String> options(Form form) {
		Map<String, String> options = dao.options();
		try {
			if (form != null && form.fieldsets != null
					&& !form.fieldsets.isEmpty()) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				for (Fieldset fieldset : form.fieldsets) {
					String key = fieldset.id.toString();
					map.put(key, fieldset.toString());
					options.remove(key);
				}
				map.putAll(options);
				return map;
			}
		} catch (Exception e) {
		}
		return options;
	}
}