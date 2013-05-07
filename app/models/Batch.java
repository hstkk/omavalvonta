package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import models.helpers.Dao;
import models.helpers.UserModel;
import org.hibernate.envers.Audited;
import play.data.format.Formats;
import play.data.validation.Constraints.Required;
import play.i18n.Messages;
import utils.Converter;
import utils.Helper;

@Entity
@Audited
public class Batch extends UserModel {

	public interface All {
	}

	public interface Step2 {
	}

	public final static Dao<Batch, Long> dao = new Dao<Batch, Long>(Batch.class);

	@Required(groups = { All.class })
	@Valid
	@ManyToOne(cascade = CascadeType.ALL)
	public Product product;

	@Required(groups = { All.class, Step2.class })
	@NotNull(groups = { All.class, Step2.class })
	@Formats.DateTime(pattern = "dd.MM.yyyy")
	public Date date;

	@Required(groups = { All.class, Step2.class })
	@NotNull(groups = { All.class, Step2.class })
	@Valid
	@OneToMany(mappedBy = "batch")
	public List<IngredientSupplyBatch> ingredientSupplies = new ArrayList<IngredientSupplyBatch>();

	@OneToOne(mappedBy = "batch", fetch = FetchType.LAZY)
	public FinalProduct finalProduct;

	@Override
	public void onCreate() {
		this.product = Product.dao.getReference(this.product);
		super.onCreate();
	}

	public String toString() {
		String separator = Helper.getOrElse("batch.separator");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Converter.dateToString(this.date,
				Helper.getOrElse("batch.date")));
		stringBuilder.append(separator);
		stringBuilder.append(this.product.id);
		stringBuilder.append(separator);
		stringBuilder.append(this.id);
		return stringBuilder.toString();
	}

	public boolean bind(User user, Product product) {
		this.user = user;
		this.product = product;
		if (ingredientSupplies.isEmpty())
			return false;

		Iterator<IngredientSupplyBatch> iterator = ingredientSupplies
				.iterator();
		while (iterator.hasNext()) {
			IngredientSupplyBatch ingredientSupplyBatch = iterator.next();
			if (ingredientSupplyBatch.amount == null)
				iterator.remove();

			boolean success = ingredientSupplyBatch.bind(this);
			if (!success)
				return false;
		}
		return true;
	}

	@Transient
	private Map<String, IngredientSupply> ingredientSupplyMap;

	public IngredientSupply getIngredientSupply(String ingredientSupplyId) {
		if (ingredientSupplyMap == null) {
			ingredientSupplyMap = new LinkedHashMap<String, IngredientSupply>();
			if (this.product.ingredients != null)
				for (Ingredient ingredient : this.product.ingredients)
					for (IngredientSupply ingredientSupply : ingredient.ingredientSupllies)
						ingredientSupplyMap.put(ingredientSupply.id.toString(),
								ingredientSupply);
		}
		return ingredientSupplyMap.get(ingredientSupplyId);
	}

	public play.data.Form<Batch> getForm() {
		for (Ingredient ingredient : this.product.ingredients)
			for (IngredientSupply ingredientSupply : ingredient.ingredientSupllies) {
				IngredientSupplyBatch ingredientSupplyBatch = new IngredientSupplyBatch(
						ingredientSupply, this);
				this.ingredientSupplies.add(ingredientSupplyBatch);
			}
		play.data.Form<Batch> form = new play.data.Form<Batch>(Batch.class);
		return form.fill(this);
	}

	public String getStatus() {
		if (this.finalProduct != null)
			return this.finalProduct.toString();
		return Messages.get("status.wip");
	}
}
