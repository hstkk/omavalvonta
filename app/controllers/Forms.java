package controllers;

import static play.data.Form.*;
import play.db.jpa.Transactional;
import play.mvc.Call;
import play.mvc.Result;
import play.mvc.With;
import play.mvc.Security.Authenticated;
import views.html.forms.*;
import controllers.helpers.SecuredCrud;
import controllers.shib.Secured;
import controllers.shib.Session;

@With(Session.class)
@Authenticated(Secured.class)
public class Forms extends SecuredCrud<models.dynamicforms.Form> {
	public Forms() {
		super(models.dynamicforms.Form.dao,
				form(models.dynamicforms.Form.class), create.ref(), page.ref(),
				show.ref(), update.ref());
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
