package forms.dynamicforms;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import models.dynamicforms.FieldType;
import models.dynamicforms.Form;
import play.data.validation.Constraints.Required;

public class Field {
	@Required
	@NotNull
	public FieldType type;

	@Required
	@NotNull
	public String name;

	public String help;

	@Required
	@NotNull
	public boolean isRequired;

	@Required
	@NotNull
	public boolean isSigned;

	public Double min;

	public Double max;

	public Field() {
	}

	public Field(models.dynamicforms.Field field) {
		if (field.id != null) {
			this.name = field.name;
			this.help = field.help;
			this.type = field.type;
			this.isRequired = field.isRequired;
			this.isSigned = field.isSigned;
			this.min = field.min;
			this.max = field.max;
		}
	}
}
