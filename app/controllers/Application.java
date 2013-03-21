package controllers;

import controllers.shib.Secured;
import controllers.shib.SessionTimeout;
import play.db.jpa.Transactional;
import play.mvc.*;
import utils.Helper;
import views.html.*;

@With(SessionTimeout.class)
public class Application extends Controller {

	@Transactional(readOnly = true)
	public static Result index() {
		int pageNumber = 0;
		return News.index(pageNumber);
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