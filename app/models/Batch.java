package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import models.helpers.Dao;
import models.helpers.Model;
import models.helpers.UserModel;
import org.hibernate.envers.Audited;

import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;
import utils.Converter;
import utils.Helper;

@Entity
@Audited
@AttributeOverride(name = "lastModified", column = @Column(nullable = false, updatable = false, name = "created"))
public class Batch extends UserModel {

	public interface All {
	}

	public interface Step1 {
	}

	public interface Step2 {
	}

	public final static Dao<Batch, Long> dao = new Dao<Batch, Long>(Batch.class);

	@Required(groups = { All.class, Step1.class })
	@ManyToOne(cascade = CascadeType.ALL)
	public Product product;

	@Required(groups = { All.class, Step2.class })
	@NotNull(groups = { All.class, Step2.class })
	@OneToMany(mappedBy = "batch")
	public List<IngredientSupplyBatch> ingredientSupplies = new ArrayList<IngredientSupplyBatch>();

	@PrePersist
	private void onPre() {
		this.product = Product.dao.getReference(this.product);
	}

	public String toString() {
		String separator = Helper.getOrElse("batch.separator");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Converter.dateToString(this.lastModified,
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
}
