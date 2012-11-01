package forms;

import javax.validation.constraints.NotNull;
import play.data.validation.Constraints.*;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
public class Term {

	Long id;

	@Required
	@NotNull
	public String name;

	public Term() {
	}

	public Term(models.Term term) {
		this.id = term.id;
		this.name = term.name;
	}
}
