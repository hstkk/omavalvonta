package controllers;

import models.IngredientSupply;
import controllers.helpers.SecuredCrud;
import play.mvc.Controller;
import views.html.ingredientsupplies.*;

public class IngredientSupplies extends Controller {
	public final static SecuredCrud<IngredientSupply> crud = new SecuredCrud<IngredientSupply>(
			IngredientSupply.crud,
			form(IngredientSupply.class),
			null,
			create.ref(),
			page.ref(),
			show.ref(),
			routes.Terms.crud.page(1));
}
