package controllers;

import models.Ingredient;
import play.mvc.Call;
import play.mvc.Controller;
import views.html.ingredients.*;
import controllers.helpers.SecuredCrud;

public class Ingredients extends Controller {
	public final static SecuredCrud<Ingredient> crud = new SecuredCrud<Ingredient>(
			Ingredient.crud,
			form(Ingredient.class),
			new Router(),
			create.ref(),
			page.ref(),
			null,
			update.ref()
		);

	public static class Router extends controllers.helpers.Router {
		@Override
		public Call page() {
			return controllers.routes.Ingredients.crud.page(1);
		}
	}
}
