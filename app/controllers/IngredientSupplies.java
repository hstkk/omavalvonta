package controllers;

import models.IngredientSupply;
import controllers.helpers.SecuredCrud;
import play.mvc.Controller;
import views.html.ingredientsupplies.*;

public class IngredientSupplies extends Controller {
	public final static SecuredCrud<IngredientSupply> crud = new SecuredCrud<IngredientSupply>(IngredientSupply.crud,
			form(IngredientSupply.class),
			null,
			create.ref(),
			page.ref(),
			show.ref(),
			routes.Terms.crud.page(1));

	/*@Transactional(readOnly = true)
	public static Result read(Long ingredientId) {
		IngredientSupply ingredientSupply = IngredientSupply
				.findById(ingredientId);
		if (ingredientSupply == null)
			return notFound();
		List<Batch> batches = Batch.findByIngredientSupply(ingredientId);
		return ok(views.html.ingredientsupplies.read.render(ingredientSupply,
				batches));
	}*/
}
