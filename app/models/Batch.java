package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import models.helpers.Crud;
import models.helpers.UserModel;
import org.hibernate.envers.Audited;

//TODO
@Entity
@Audited
public class Batch extends UserModel {
	public final static Crud<Batch, Long> crud = new Crud<Batch, Long>(Batch.class);

	@OneToMany(mappedBy = "batch")
	public List<IngredientSupplyBatch> ingredientSupplies = new ArrayList<IngredientSupplyBatch>();
}
