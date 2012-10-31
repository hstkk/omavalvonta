package forms;

import javax.validation.constraints.NotNull;
import play.data.validation.Constraints.*;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
public class Term {
	@Required
	@NotNull
	public String name;
}
