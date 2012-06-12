package controllers;

import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import play.mvc.Result;
import views.html.*;

public class Batches extends Controller {

	@Transactional(readOnly = true)
	public static Result index() {
		return page(1);
	}

	@Transactional(readOnly = true)
	public static Result page(int page) {
		return TODO;
	}
	
	public static Result add() {
		return TODO;
	}

	@Transactional
	public static Result save() {
		return TODO;
	}
}
