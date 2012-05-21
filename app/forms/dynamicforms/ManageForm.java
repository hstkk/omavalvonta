package forms.dynamicforms;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Lob;

import play.data.validation.Constraints.Required;

public class ManageForm {
	@Required
	public String name;

	@Lob
	public String text;

	public List<Integer> fields;

	@Required
	public Boolean isActive;

	public ManageForm() {
	}

	public ManageForm(models.dynamicforms.Form form) {
		this.name = form.content.name;
		this.text = form.content.text;
		this.isActive = form.isActive;
		fields = new ArrayList<Integer>();
		for (models.dynamicforms.Field field : form.fields)
			fields.add(field.id);
	}
}
