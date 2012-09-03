package forms;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import models.helpers.KeyValue;
import models.helpers.KeyValueBatch;
import play.data.validation.Constraints.*;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 */
public class Batch {
	@Required
	@NotNull
	public models.Product product;

	@Required
	@NotNull
	public List<KeyValueBatch> keyvalue = new ArrayList<KeyValueBatch>();

	public Batch() {
	}

	public String validate() {
		String result = null;
		if (keyvalue.isEmpty())
			result = "Raaka-aineita ei ole valittu";
		return result;
	}
}
