package forms.dynamicforms;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import models.Batch;
import models.dynamicforms.Field;
import play.data.validation.Constraints.*;
import play.db.jpa.Transactional;
import utils.Validation;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
public class Dynamic {
	public Long id;

	// @Required
	// @OneToMany
	public List<Fieldset> values;

	/**
	 * Validates the form data.
	 * 
	 * @return null if no errors else errors.
	 */
	@Transactional(readOnly = true)
	public String validate() {
		StringBuilder result = new StringBuilder();
		if (values != null) {
			for (Fieldset fieldset : values) {
				if (fieldset != null
						&& (fieldset.ack || !fieldset.value.isEmpty() || !fieldset.comment
								.isEmpty())) {
					Field field = Field.findById(fieldset.fieldId);
					if (field != null) {
						switch (field.fieldType) {
						case CHECKBOX:
							if (Validation.isBoolean(fieldset.value) == null)
								result.append("Kentän " + field.name
										+ " arvo ei ole ok. ");
							break;
						case DATE:
							if (Validation.isDate(fieldset.value) == null)
								result.append("Kentän "
										+ field.name
										+ " arvo ei ole päivämäärä (pp.kk.vvvv. ");
							break;
						case DOUBLE:
							Double doubleTest = Validation
									.isDouble(fieldset.value);
							if (doubleTest == null)
								result.append("Kentän " + field.name
										+ " arvon kuuluu olla numero. ");
							else if (field.min != null
									&& !Validation.min(doubleTest, field.min))
								result.append("Kentän " + field.name
										+ " arvo voi olla pienimmillään "
										+ field.min + ". ");
							else if (field.max != null
									&& !Validation.min(doubleTest, field.max))
								result.append("Kentän " + field.name
										+ " arvo voi olla suurimmillaan "
										+ field.max + ". ");
							break;
						case INT:
							Integer intTest = Validation.isInt(fieldset.value);
							if (intTest == null)
								result.append("Kentän " + field.name
										+ " arvon kuuluu olla numero. ");
							else if (field.min != null
									&& !Validation.min(intTest, field.min))
								result.append("Kentän " + field.name
										+ " arvo voi olla pienimmillään "
										+ field.min + ". ");
							else if (field.max != null
									&& !Validation.min(intTest, field.max))
								result.append("Kentän " + field.name
										+ " arvo voi olla suurimmillaan "
										+ field.max + ". ");
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
		}
		return (result.length() == 0) ? "" : result.toString();
	}

	public Dynamic() {
		this.values = new ArrayList<Fieldset>();
	}

	public Dynamic(String... values) {
		this.values = new ArrayList<Fieldset>();
		for (String value : values)
			System.out.println(value);
	}

	public Dynamic(Long id) {
		this.id = id;
	}
}