package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import models.helpers.Crud;
import models.helpers.UserModel;
import org.hibernate.envers.Audited;
import play.data.validation.Constraints.Required;
import utils.Converter;
import utils.Helper;

@Entity
@Audited
@AttributeOverride(name = "lastModified", column = @Column(nullable = false, updatable = false, name = "created"))
public class Batch extends UserModel {
	public final static Crud<Batch, Long> crud = new Crud<Batch, Long>(
			Batch.class);

	@Required
	@ManyToOne(cascade = CascadeType.ALL)
	public Product product;

	@Required
	@NotNull
	@OneToMany(mappedBy = "batch")
	public List<IngredientSupplyBatch> ingredientSupplies = new ArrayList<IngredientSupplyBatch>();

	@PrePersist
	private void onPre() {
		this.product = Product.crud.getReference(this.product);
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
}
