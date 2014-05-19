package controllers;

import static play.data.Form.form;
import models.IngredientSupply;
import models.helpers.Dao;
import models.helpers.Page;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.F;
import play.mvc.Call;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import play.mvc.With;
import views.html.ingredientsupplies.create;
import views.html.ingredientsupplies.page;
import views.html.ingredientsupplies.show;
import controllers.helpers.UserCrud;
import controllers.shib.Secured;
import controllers.shib.Session;

@With(Session.class)
public class IngredientSupplies extends UserCrud<IngredientSupply> {

	public IngredientSupplies() {
		super(
			F.Option.<Dao<IngredientSupply, Long>>Some(IngredientSupply.dao),
			F.Option.<Form<IngredientSupply>>Some(form(IngredientSupply.class)),
			F.Option.<Template1<Form<IngredientSupply>, Html>>Some(create.ref()),
			F.Option.<Template1<Page<IngredientSupply>, Html>>Some(page.ref()),
			F.Option.<Template1<IngredientSupply, Html>>Some(show.ref()),
			F.Option.<Template2<IngredientSupply, Form<IngredientSupply>, Html>>None()
		);
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