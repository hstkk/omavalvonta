package controllers.dynamicforms;

import models.Batch;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

public class Results extends Controller {

	@Transactional(readOnly = true)
	public static Result add(Long batchId, String program) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound(views.html.notFound.render());
		return TODO;
	}

	@Transactional
	public static Result save(Long batchId, String program) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound(views.html.notFound.render());
		return TODO;
	}

	@Transactional(readOnly = true)
	public static Result show(Long batchId, String program) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Results results = models.dynamicforms.Results.findByIdAndType(batchId, program);
		if (results == null)
			return notFound(views.html.notFound.render());
		return TODO;
	}

	@Transactional(readOnly = true)
	public static Result history(Long batchId, String program) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Results results = models.dynamicforms.Results.findByIdAndType(batchId, program);
		if (results == null)
			return notFound(views.html.notFound.render());
		return TODO;
	}
}
