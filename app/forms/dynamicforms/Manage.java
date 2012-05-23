package forms.dynamicforms;

import javax.persistence.Lob;
import javax.validation.Valid;

import play.data.validation.Constraints.Required;

public class Manage {
	public Long id;
	
	public Long basedOn;

	@Required
	@Valid
	public String name;

	@Lob
	public String description;

	@Required
	public Boolean isActive;

	public Manage() {
	}

	public Manage(models.dynamicforms.Form form) {
		if (form != null) {
			this.id = form.id;
			this.name = form.name;
			if(form.basedOn != null)
					this.basedOn = form.basedOn.id;
			this.description = form.description;
			this.isActive = form.isActive;
		}
	}
}
