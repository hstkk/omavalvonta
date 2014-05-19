package controllers;

import static play.data.Form.form;
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
import views.html.forms.create;
import views.html.forms.page;
import views.html.forms.show;
import views.html.forms.update;
import controllers.helpers.SecuredCrud;
import controllers.shib.Secured;
import controllers.shib.Session;

@With(Session.class)
@Authenticated(Secured.class)
public class Forms extends SecuredCrud<models.dynamicforms.Form> {
	public Forms() {
		super(
			F.Option.<Dao<models.dynamicforms.Form, Long>>Some(models.dynamicforms.Form.dao),
			F.Option.<Form<models.dynamicforms.Form>>Some(form(models.dynamicforms.Form.class)),
			F.Option.<Template1<Form<models.dynamicforms.Form>, Html>>Some(create.ref()),
			F.Option.<Template1<Page<models.dynamicforms.Form>, Html>>Some(page.ref()),
			F.Option.<Template1<models.dynamicforms.Form, Html>>Some(show.ref()),
			F.Option.<Template2<models.dynamicforms.Form, Form<models.dynamicforms.Form>, Html>>Some(update.ref())
		);
	}

	@Override
	public Call callPage() {
		return controllers.routes.Forms.page(1);
	}

	@Override
	public Call callShow(Long id) {
		return controllers.routes.Forms.show(id);
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
