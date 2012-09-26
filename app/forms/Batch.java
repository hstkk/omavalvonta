package forms;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import forms.dynamicforms.Fieldset;

import models.helpers.KeyValue;
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
	public List<Ingredients> values;

	public Batch() {
	}

	public String validate() {
		String result = null;
		if (values.isEmpty())
			result = "Raaka-aineita ei ole valittu";
		return result;
	}
}
