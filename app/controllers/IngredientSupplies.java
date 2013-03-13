package controllers;

import models.IngredientSupply;
import play.mvc.Call;
import play.mvc.Controller;
import views.html.ingredientsupplies.*;
import controllers.helpers.UserCrud;
import controllers.helpers.UserRouter;

public class IngredientSupplies extends Controller {
	public final static UserCrud<IngredientSupply> crud = new UserCrud<IngredientSupply>(
			IngredientSupply.crud,
			form(IngredientSupply.class),
			new Router(),
			create.ref(),
			page.ref(),
			show.ref(),
			null
		);

	public static class Router extends UserRouter {
		@Override
		public Call ack(Long id) {
			// TODO ack url
			return super.ack(id);
		}

		@Override
		public play.mvc.Call page() {
			return controllers.routes.IngredientSupplies.crud.page(1);
		}

		@Override
		public play.mvc.Call show(Long id) {
			return controllers.routes.IngredientSupplies.crud.show(id);
		}
	}
}