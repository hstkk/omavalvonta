package controllers;

import models.IngredientSupply;
import play.api.mvc.Call;
import views.html.ingredientsupplies.*;
import controllers.helpers.UserCrud;

public class IngredientSupplies extends UserCrud<IngredientSupply> {
	public IngredientSupplies() {
		super(
				IngredientSupply.crud,
				form(IngredientSupply.class),
				null, // update
				create.ref(),
				page.ref(),
				show.ref()
		);
	}

	@Override
	protected Call callPage() {
		return controllers.routes.App.ingredientSupplies.page(1);
	}

	@Override
	protected Call callShow(Long id) {
		return controllers.routes.App.ingredientSupplies.show(id);
	}
}
