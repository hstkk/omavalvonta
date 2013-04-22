package models.dynamicforms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import models.helpers.Dao;
import models.helpers.UserModel;
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fieldset")
	@OrderColumn(name = "fields_index")
	@Valid
	public List<Field> fields = new ArrayList<Field>();

	public String toString() {
		return name;
	}

	@PrePersist
	@PreUpdate
	private void onPre() {
		for (Field field : this.fields)
			field.setFieldset(this);
	}

	public static Map<String, String> options(String formId) {
		Map<String, String> options = dao.options();
		try {
			Long id = Long.parseLong(formId);
			Form form = Form.dao.findById(id);
			if (form != null && form.fieldsets != null
					&& !form.fieldsets.isEmpty()) {
				Map<String, String> map = new HashMap<String, String>();
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