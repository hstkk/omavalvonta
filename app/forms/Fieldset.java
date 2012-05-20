package forms;

import javax.persistence.Lob;
import play.data.validation.Constraints.*;

public class Fieldset {
	@Required
	int fieldId;

	public String value;

	public Boolean ack;

	@Lob
	public String comment;
}
