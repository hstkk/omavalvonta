package controllers.dynamicforms;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

public class Results extends Controller {

	@Transactional(readOnly = true)
	public static Result add(Long batchId, String program) {
		return TODO;
	}

	@Transactional
	public static Result save(Long batchId, String program) {
		return TODO;
	}

	@Transactional(readOnly = true)
	public static Result show(Long batchId, String program) {
		return TODO;
	}

	@Transactional(readOnly = true)
	public static Result history(Long batchId, String program) {
		return TODO;
	}
}
