package forms;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import play.data.validation.Constraints.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Product.
 * 
 * @author Sami Hostikka <dev@01.fi>
 */
public class Product {
	public Long id;

	/** The name. */
	@Required
	@NotNull
	public String name;

	/** The description. */
	@Lob
	public String description;

	public List<Long> ingredientIds = new ArrayList<Long>();

	/** The forms. */
	public List<Long> formIds = new ArrayList<Long>();

	public Product() {
	}

	public Product(models.Product product) {
		this.id = product.id;
		this.name = product.name;
		this.description = product.description;
	}
}
