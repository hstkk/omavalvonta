package forms.dynamicforms;

import javax.persistence.Lob;
import play.data.validation.Constraints.*;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
public class Fieldset {
	@Required
	public Long fieldId;

	public String value;

	public Boolean ack;

	@Lob
	public String comment;

	public Fieldset() {
	}

	public Fieldset(Long fieldId, String value, Boolean ack, String comment) {
		this.fieldId = fieldId;
		this.value = value;
		this.ack = ack;
	}
}
