package controllers;

import play.*;
import play.db.jpa.Transactional;
import play.mvc.*;
import java.util.*;

import views.html.*;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render());
	}

	public static Result management() {
		return ok(management.render());
	}
}