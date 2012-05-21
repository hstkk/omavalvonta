package forms.dynamicforms;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Lob;

import play.data.validation.Constraints.Required;

public class Manage {
	@Required
	public String name;

	@Lob
	public String description;

	public List<Integer> fields;

	@Required
	public Boolean isActive;

	public Manage() {
	}

	public Manage(models.dynamicforms.Form form) {
		this.name = form.name;
		this.description = form.description;
		this.isActive = form.isActive;
		fields = new ArrayList<Integer>();
		for (models.dynamicforms.Field field : form.fields)
			fields.add(field.id);
	}
}
