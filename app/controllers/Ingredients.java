package controllers;

import models.Ingredient;
import play.api.mvc.Call;
import views.html.ingredients.*;
import controllers.helpers.SecuredCrud;

public class Ingredients extends SecuredCrud<Ingredient> {
	public Ingredients() {
		super(
				Ingredient.crud,
				form(Ingredient.class),
				update.ref(),
				create.ref(),
				page.ref(),
				null // show
		);
	}

	@Override
	protected Call callPage() {
		return controllers.routes.App.ingredients.page(1);
	}
}
