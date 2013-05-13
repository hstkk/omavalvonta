package controllers;

import models.helpers.Page;
import controllers.shib.Secured;
import controllers.shib.Session;
import play.db.jpa.Transactional;
import play.mvc.*;
import play.mvc.Security.Authenticated;
import utils.Helper;
import views.html.*;

@With(Session.class)
public class Application extends Controller {

	@Transactional(readOnly = true)
	public static Result index(int pageNumber) {
		Page<models.News> page = models.News.dao.page(pageNumber);
		return ok(news.index.render(page));
	}

	@Authenticated(Secured.class)
	@Transactional(readOnly = true)
	public static Result management() {
		if (Secured.isAdmin())
			return redirect(routes.News.page(1));
		return Helper.getUnauthorized();
	}

	@Transactional
	public static Result test() {
		if (models.User.dao.count() == 0) {
			models.User user = new models.User();
			user.email = "foo@bar.com";
			user.role = "stuff";
			models.User.dao.create(user);
			utils.ShibbolethHelper.createSession(ctx(), user);
		} else {
			models.User user = models.User.dao.findById((long) 1);
			utils.ShibbolethHelper.createSession(ctx(), user);
		}
		return index(1);
	}
}