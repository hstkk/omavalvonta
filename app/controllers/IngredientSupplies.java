package controllers;

import models.IngredientSupply;
import play.mvc.Controller;
import views.html.ingredientsupplies.create;
import views.html.ingredientsupplies.page;
import views.html.ingredientsupplies.show;
import controllers.helpers.UserCrud;

public class IngredientSupplies extends Controller {
	public final static UserCrud<IngredientSupply> crud = new UserCrud<IngredientSupply>(
			IngredientSupply.crud,
			form(IngredientSupply.class),
			null,
			create.ref(),
			page.ref(),
			show.ref(),
			routes.Terms.crud.page(1));
}
