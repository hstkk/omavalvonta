package forms;

import models.Field;
import javax.persistence.Lob;
import play.data.validation.Constraints.*;

public class Fieldset {
	@Required
	int fieldId;

	public String value;

	public Boolean ack;

	@Lob
	public String comment;

	//TODO Localize
	//TODO isrequired
	public String validate() {
		String result = null;
		Field field = Field.findById(fieldId);
		if (field != null)
			result = "Virhe! Kenttää ei löytynyt.";
		else {
			if (Field.type.CHECKBOX == field.type)
				try {
					Boolean.parseBoolean(value);
				} catch (Exception e) {
					result = "Ei validi";
				}
			else if (Field.type.DOUBLE == field.type)
				try {
					Double valueDouble = Double.parseDouble(value);
					if ((field.hardMin != null && valueDouble < field.hardMin)
							|| (field.hardMin != null && valueDouble > field.hardMax))
						result = "Arvo ei ole rajojen puitteissa "
								+ field.hardMin + "-" + field.hardMax;
				} catch (Exception e) {
					result = "Ei validi";
				}
			else if (Field.type.INT == field.type)
				try {
					int valueInt = Integer.parseInt(value);
					if ((field.hardMin != null && valueInt < field.hardMin)
							|| (field.hardMin != null && valueInt > field.hardMax))
						result = "Arvo ei ole rajojen puitteissa "
								+ field.hardMin + "-" + field.hardMax;
				} catch (Exception e) {
					result = "Ei validi";
				}
		}
		return result;
	}
}
