package controllers;

import static play.data.Form.form;
import models.dynamicforms.Fieldset;
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
import views.html.fieldsets.create;
import views.html.fieldsets.page;
import views.html.fieldsets.show;
import views.html.fieldsets.update;
import controllers.helpers.SecuredCrud;
import controllers.shib.Secured;
import controllers.shib.Session;

@With(Session.class)
@Authenticated(Secured.class)
public class Fieldsets extends SecuredCrud<Fieldset> {
	public Fieldsets() {
		super(
			F.Option.<Dao<Fieldset, Long>>Some(Fieldset.dao),
			F.Option.<Form<Fieldset>>Some(form(Fieldset.class)),
			F.Option.<Template1<Form<Fieldset>, Html>>Some(create.ref()),
			F.Option.<Template1<Page<Fieldset>, Html>>Some(page.ref()),
			F.Option.<Template1<Fieldset, Html>>Some(show.ref()),
			F.Option.<Template2<Fieldset, Form<Fieldset>, Html>>Some(update.ref())
	);
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
