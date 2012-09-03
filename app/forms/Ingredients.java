package forms;

import javax.persistence.Lob;
import play.data.validation.Constraints.*;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
public class Ingredients {
	@Required
	public Long id;

	public Double amount;
}
