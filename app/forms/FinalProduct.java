package forms;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import play.data.validation.Constraints.*;

import models.Term;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
public class FinalProduct {

	@Required
	@NotNull
	public Term destiny;

	@Required
	@NotNull
	public Double amount;

	@Required
	@NotNull
	public Term unit;

	@Lob
	public String comment;

	public FinalProduct() {
	}

	public FinalProduct(models.FinalProduct finalProduct) {
		this.destiny = finalProduct.destiny;
		this.amount = finalProduct.amount;
		this.unit = finalProduct.unit;
		this.comment = finalProduct.comment;
	}
}