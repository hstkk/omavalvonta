package controllers;

import controllers.shib.Secured;
import controllers.shib.SessionTimeout;
import play.mvc.*;
import views.html.*;

@With(SessionTimeout.class)
public class Application extends Controller {

	public static Result index() {
		return ok(index.render());
	}

	@Security.Authenticated(Secured.class)
	public static Result management() {
		return ok(management.render());
	}
}