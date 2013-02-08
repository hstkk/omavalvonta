package controllers;

import models.Ingredient;
import models.IngredientSupply;
import models.Product;
import models.helpers.KeyValue;
import controllers.helpers.Crud;
import static play.data.Form.*;
import play.Routes;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

//TODO API
public class Api extends Controller {
	@Transactional(readOnly = true)
	public static Result batchIngredients(Long productId) {
		Product product = Product.crud.findById(productId);
		if (product == null)
			return notFound();
		if (product.ingredients == null || product.ingredients.isEmpty())
			return ok("Tuotteen raaka-aineita ei löytynyt");
		StringBuilder stringBuilder = new StringBuilder();
		int index = 0;
		/*for (Ingredient ingredient : product.ingredients) {
			KeyValue<String, Integer> keyvalue = IngredientSupply
					.findAliveByIngredient(ingredient, index);
			index = keyvalue.value;
			stringBuilder.append(keyvalue.key);
		}*/
		return ok(stringBuilder.toString());
	}

	public static Result javascriptRoutes() {
		response().setContentType("text/javascript");
		return ok(
			Routes.javascriptRouter("routes",
				controllers.routes.javascript.Api.batchIngredients()
			)
		);
	}
}
