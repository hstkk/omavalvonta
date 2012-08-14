package controllers;

import play.db.jpa.Transactional;
import play.mvc.*;

public class Ingredients extends Controller {
	@Transactional(readOnly = true)
	public static Result create() {
		return TODO;
	}

	@Transactional(readOnly = true)
	public static Result index() {
		return TODO;
	}

	@Transactional(readOnly = true)
	public static Result page() {
		return TODO;
	}

	@Transactional(readOnly = true)
	public static Result read() {
		return TODO;
	}

	@Transactional
	public static Result save() {
		return TODO;
	}
}
