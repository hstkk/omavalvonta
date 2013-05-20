package controllers;

import models.helpers.Page;
import controllers.shib.Secured;
import controllers.shib.Session;
import play.db.jpa.Transactional;
import play.mvc.*;
import play.mvc.Security.Authenticated;
import utils.Helper;
import views.html.news.*;

@With(Session.class)
public class Application extends Controller {

	@Transactional(readOnly = true)
	public static Result index(int pageNumber) {
		Page<models.News> page = models.News.dao.page(pageNumber);
		return ok(index.render(page));
	}

	@Authenticated(Secured.class)
	@Transactional(readOnly = true)
	public static Result management() {
		if (Secured.isAdmin())
			return redirect(routes.News.page(1));
		return Helper.getUnauthorized();
	}
}