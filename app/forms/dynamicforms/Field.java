package forms.dynamicforms;

import models.dynamicforms.FieldType;
import play.data.validation.Constraints.Required;

public class Field {
	@Required
	public FieldType type;

	@Required
	public String name;

	public String help = "";

	@Required
	public boolean isRequired;

	@Required
	public boolean isSigned;

	public Double softMin = null;

	public Double softMax = null;

	public Field() {
	}

	public Field(models.dynamicforms.Field field) {
		if (field != null) {
			this.name = field.name;
			this.help = field.help;
			this.type = field.type;
			this.isRequired = field.isRequired;
			this.isSigned = field.isSigned;
			this.softMin = field.softMin;
			this.softMax = field.softMax;
		}
	}
}
