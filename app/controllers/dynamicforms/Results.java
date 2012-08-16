package controllers.dynamicforms;

import java.util.List;

import forms.dynamicforms.Dynamic;

import models.Batch;
import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class Results extends Controller {

	final static Form<Dynamic> FORM = form(Dynamic.class);

	@Transactional(readOnly = true)
	public static Result create(Long batchId, Long formId) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Form f = models.dynamicforms.Form.findById(formId);
		if (f == null)
			return notFound(views.html.notFound.render());
		return TODO;
	}

	@Transactional
	public static Result save(Long batchId, Long formId) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Form f = models.dynamicforms.Form.findById(formId);
		if (f == null)
			return notFound(views.html.notFound.render());
		return TODO;
	}

	@Transactional(readOnly = true)
	public static Result read(Long batchId, Long formId) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Form f = models.dynamicforms.Form.findById(formId);
		if (f == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Results results = models.dynamicforms.Results
				.findByBatchAndForm(batchId, formId);
		if(results == null)
			return notFound(views.html.notFound.render());
		return TODO;
	}

	@Transactional(readOnly = true)
	public static Result history(Long batchId, Long formId) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Form f = models.dynamicforms.Form.findById(formId);
		if (f == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Results results = models.dynamicforms.Results.findByBatchAndForm(batchId, formId);
		if(results == null)
			return notFound(views.html.notFound.render());
		return TODO;
	}

	@Transactional(readOnly = true)
	public static Result pdfify(Long batchId, Long formId) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Form f = models.dynamicforms.Form.findById(formId);
		if (f == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Results results = models.dynamicforms.Results.findByBatchAndForm(batchId, formId);
		if(results == null)
			return notFound(views.html.notFound.render());
		return TODO;
	}
}
