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
public class Fields extends SecuredCrud<models.dynamicforms.Field> {
	public Fields() {
		super(models.dynamicforms.Field.dao,
				form(models.dynamicforms.Field.class), create.ref(), null,
				null, update.ref());
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
