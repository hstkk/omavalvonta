package controllers;

import models.helpers.Page;
import controllers.shib.Secured;
import controllers.shib.Session;
import play.db.jpa.Transactional;
import play.mvc.*;
import utils.Helper;
import views.html.*;

@With(Session.class)
public class Application extends Controller {

	@Transactional(readOnly = true)
	public static Result index(int pageNumber) {
		Page<models.News> page = models.News.dao.page(pageNumber);
		return ok(views.html.news.index.render(page));
	}

	@Security.Authenticated(Secured.class)
	public static Result management() {
		if (Secured.isAdmin())
			return ok(management.render());
		return unauthorized(views.html.error.render(
				Helper.getMessage("http.401"),
				Helper.getMessage("http.401.description")));
	}
}