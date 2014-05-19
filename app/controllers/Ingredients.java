package controllers;

import static play.data.Form.form;
import models.Ingredient;
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
import views.html.ingredients.create;
import views.html.ingredients.page;
import views.html.ingredients.update;
import controllers.helpers.SecuredCrud;
import controllers.shib.Secured;
import controllers.shib.Session;

@With(Session.class)
@Authenticated(Secured.class)
public class Ingredients extends SecuredCrud<Ingredient> {
	public Ingredients() {
		super(
			F.Option.<Dao<Ingredient, Long>>Some(Ingredient.dao),
			F.Option.<Form<Ingredient>>Some(form(Ingredient.class)),
			F.Option.<Template1<Form<Ingredient>, Html>>Some(create.ref()),
			F.Option.<Template1<Page<Ingredient>, Html>>Some(page.ref()),
			F.Option.<Template1<Ingredient, Html>>None(),
			F.Option.<Template2<Ingredient, Form<Ingredient>, Html>>Some(update.ref())
		);
		
	}

	@Override
	public Call callPage() {
		return controllers.routes.Ingredients.page(1);
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
	@Transactional
	public Result update(Long id) {
		return super.update(id);
	}
}
