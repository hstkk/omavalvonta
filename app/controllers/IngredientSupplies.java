package controllers;

import models.IngredientSupply;
import static play.data.Form.*;
import play.db.jpa.Transactional;
import play.mvc.Call;
import play.mvc.Result;
import play.mvc.With;
import play.mvc.Security.Authenticated;
import views.html.ingredientsupplies.*;
import controllers.helpers.UserCrud;
import controllers.shib.Secured;
import controllers.shib.Session;

@With(Session.class)
public class IngredientSupplies extends UserCrud<IngredientSupply> {

	public IngredientSupplies() {
		super(IngredientSupply.dao, form(IngredientSupply.class), create.ref(),
				page.ref(), show.ref(), null);
	}

	@Override
	@Authenticated(Secured.class)
	@Transactional
	public Result ack(Long id) {
		return super.ack(id);
	}

	@Override
	public Call callPage() {
		return controllers.routes.IngredientSupplies.page(1);
	}

	@Override
	public Call callShow(Long id) {
		return controllers.routes.IngredientSupplies.show(id);
	}

	@Override
	@Transactional
	public Result create() {
		return super.create();
	}

	@Override
	@Transactional(readOnly = true)
	public Result page(int pageNumber) {
		return super.page(pageNumber);
	}

	@Override
	@Transactional(readOnly = true)
	public Result show(Long id) {
		return super.show(id);
	}
}