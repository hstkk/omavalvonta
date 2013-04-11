package controllers;

import models.helpers.Dao;
import models.helpers.Page;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import static play.data.Form.*;
import play.db.jpa.Transactional;
import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import play.mvc.Security.Authenticated;
import views.html.forms.*;
import controllers.helpers.SecuredCrud;
import controllers.shib.Secured;
import controllers.shib.Session;

@With(Session.class)
@Authenticated(Secured.class)
public class Fieldsets extends SecuredCrud<models.dynamicforms.Fieldset> {
	public Fieldsets() {
		super(models.dynamicforms.Fieldset.dao,
				form(models.dynamicforms.Fieldset.class), create.ref(), page
						.ref(), show.ref(), update.ref());
	}

	@Override
	public Call callPage() {
		return controllers.routes.Fieldsets.page(1);
	}

	@Override
	public Call callShow(Long id) {
		return controllers.routes.Fieldsets.show(id);
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

	@Override
	@Transactional
	public Result update(Long id) {
		return super.update(id);
	}
}
