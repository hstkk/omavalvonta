package controllers;

import controllers.shib.Secured;
import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.db.jpa.*;
import views.html.*;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render());
	}

	@Security.Authenticated(Secured.class)
	public static Result management() {
		return ok(management.render());
	}
}