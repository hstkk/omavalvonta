package controllers;

import models.Ingredient;
import controllers.helpers.SecuredCrud;
import play.mvc.Controller;
import views.html.ingredients.*;

public class Ingredients extends Controller {

	/*public final static SecuredCrud<Ingredient> crud = new SecuredCrud<Ingredient>(Ingredient.crud,
			form(Ingredient.class),
			update.ref(),
			create.ref(),
			page.ref(),
			null,
			routes.Ingredients.crud.page(1));*/

	static SecuredCrud<Ingredient> crud;

	public static SecuredCrud<Ingredient> getCrud() {
		if (crud == null)
			crud = new SecuredCrud<Ingredient>(Ingredient.crud,
					form(Ingredient.class),
					update.ref(),
					create.ref(),
					page.ref(),
					null,
					routes.Ingredients.crud.page(1));
		return crud;
	}
}
