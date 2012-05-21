package forms.dynamicforms;

import javax.persistence.Lob;
import play.data.validation.Constraints.Required;

public class Manage {
	public Long basedOn;

	@Required
	public String name;

	@Lob
	public String description;

	@Required
	public Boolean isActive;

	public Manage() {
	}

	public Manage(models.dynamicforms.Form form) {
		if (form.id != null) {
			this.name = form.name;
			this.basedOn = form.basedOn.id;
			this.description = form.description;
			this.isActive = form.isActive;
		}
	}
}
