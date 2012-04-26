package forms;

import java.util.List;

import models.Field;
import models.Batch;
import play.data.validation.Constraints.*;

public class Dynamic {
	@Required
	public String batch;
	
	@Required
	List<Fieldset> fieldset;
}