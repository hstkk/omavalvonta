package forms;

import java.util.List;

import models.Field;
import models.Batch;
import play.data.validation.Constraints.*;

public class Dynamic {
	@Required
	public String batch;

	@Required
	List<Fieldset> values;

	public String validate() {
		StringBuilder result = new StringBuilder();
		for (Fieldset fieldset : values) {
			if (fieldset.ack || !fieldset.value.isEmpty()
					|| !fieldset.comment.isEmpty()) {
				Field field = Field.findById(fieldset.fieldId);
				if(field != null){

				}
				else
					result.append("Virhe, lomakkeen kenttää ei löytynyt");
			}
		}
		return (result.length() == 0) ? null : result.toString();
	}
}