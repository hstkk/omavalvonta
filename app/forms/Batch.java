package forms;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
	public List<Long> ingredientSupplyId = new ArrayList<Long>();

	@Required
	@NotNull
	public List<Double> amount = new ArrayList<Double>();

	public Batch() {
	}

	public String validate() {
		String result = null;
		if (ingredientSupplyId.isEmpty() || amount.isEmpty()
				|| ingredientSupplyId.size() != amount.size())
			result = "Raaka-aineita ei ole valittu";
		return result;
	}
}
