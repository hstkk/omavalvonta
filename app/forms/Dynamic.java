package forms;

import java.util.List;

import models.Field;
import play.data.validation.Constraints.*;
import play.db.jpa.Transactional;
import utils.Validation;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
public class Dynamic {
	@Required
	public String batch;

	@Required
	List<Fieldset> values;

	/**
	 * Validates the form data.
	 * 
	 * @return null if no errors else errors.
	 */
	@Transactional(readOnly=true)
	public String validate() {
		StringBuilder result = new StringBuilder();
		for (Fieldset fieldset : values) {
			if (fieldset.ack || !fieldset.value.isEmpty()
					|| !fieldset.comment.isEmpty()) {
				Field field = Field.findById(fieldset.fieldId);
				if (field != null) {
					switch (field.type) {
					case CHECKBOX:
						if (Validation.isBoolean(fieldset.value) == null)
							result.append("Kentän " + field.name
									+ " arvo ei ole ok. ");
						break;
					case DATE:
						if (Validation.isDate(fieldset.value) == null)
							result.append("Kentän " + field.name
									+ " arvo ei ole päivämäärä (pp.kk.vvvv. ");
						break;
					case DOUBLE:
						Double doubleTest = Validation.isDouble(fieldset.value);
						if (doubleTest == null)
							result.append("Kentän " + field.name
									+ " arvon kuuluu olla numero. ");
						else if (field.hardMin != null
								&& !Validation.min(doubleTest, field.hardMin))
							result.append("Kentän " + field.name
									+ " arvo voi olla pienimmillään "
									+ field.hardMin + ". ");
						else if (field.hardMax != null
								&& !Validation.min(doubleTest, field.hardMax))
							result.append("Kentän " + field.name
									+ " arvo voi olla suurimmillaan "
									+ field.hardMax + ". ");
						break;
					case INT:
						Integer intTest = Validation.isInt(fieldset.value);
						if (intTest == null)
							result.append("Kentän " + field.name
									+ " arvon kuuluu olla numero. ");
						else if (field.hardMin != null
								&& !Validation.min(intTest, field.hardMin))
							result.append("Kentän " + field.name
									+ " arvo voi olla pienimmillään "
									+ field.hardMin + ". ");
						else if (field.hardMax != null
								&& !Validation.min(intTest, field.hardMax))
							result.append("Kentän " + field.name
									+ " arvo voi olla suurimmillaan "
									+ field.hardMax + ". ");
						break;
					case TEXT:
					case TEXTAREA:
						if (!Validation.isString(fieldset.value))
							result.append("Kentän " + field.name
									+ " arvo ei voi olla tyhjä.");
						break;
					}
				} else
					result.append("Virhe, lomakkeen kenttää ei löytynyt. ");
			}
		}
		return (result.length() == 0) ? null : result.toString();
	}
}