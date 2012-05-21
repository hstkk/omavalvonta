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
	Long fieldId;

	public String value;

	public Boolean ack;

	@Lob
	public String comment;
}
