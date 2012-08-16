package controllers.dynamicforms;

import java.util.List;

import controllers.Batches;

import forms.dynamicforms.Dynamic;

import models.Batch;
import models.Product;
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
		models.dynamicforms.Results results = models.dynamicforms.Results
				.findByBatchAndForm(batch.id, f.id);
		String html;
		if (results == null) {
			html = f.html;
			if (html == null || html.equals(""))
				return notFound(views.html.notFound.render());
			return ok(views.html.dynamicforms.results.manage.render(FORM,
					batch, f, html));
		}
		html = utils.Form.formify(models.dynamicforms.Result
				.findByResults(results.id));
		if (html == null || html.equals(""))
			return notFound(views.html.notFound.render());
		return ok(views.html.dynamicforms.results.manage.render(FORM, batch, f,
				html));
	}

	@Transactional
	public static Result save(Long batchId, Long formId) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Form f = models.dynamicforms.Form.findById(formId);
		if (f == null)
			return notFound(views.html.notFound.render());
		Form<Dynamic> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Tuloksen tallennus peruutettu!");
			//return redirect(routes.Batches.read(batchId));
			return Batches.read(batchId);
		}
		Dynamic dynamic = filledForm.get();
		if (!filledForm.hasErrors()) {
			models.dynamicforms.Results results = new models.dynamicforms.Results(
					batch, dynamic.values, f);
			if (results.save()) {
				flash("status", "Lomake on tallennettu onnistuneesti!");
				//return redirect(controllers.dynamicforms.routes.Results.read(
				//		batchId, formId));
				return read(batchId, formId);
			}
		}
		flash("status", "Lomakkeen tallennus ei onnistunut!");
		return badRequest(views.html.dynamicforms.results.manage.render(
				filledForm, batch, f, utils.Form.formify(dynamic.values)));
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
		if (results == null)
			return notFound(views.html.notFound.render());
		List<models.dynamicforms.Result> r = models.dynamicforms.Result
				.findByResults(results.id);
		return ok(views.html.dynamicforms.results.read.render(batch, f,
				results, r));
	}

	@Transactional(readOnly = true)
	public static Result history(Long batchId, Long formId) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Form f = models.dynamicforms.Form.findById(formId);
		if (f == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Results results = models.dynamicforms.Results
				.findByBatchAndForm(batchId, formId);
		if (results == null)
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
		models.dynamicforms.Results results = models.dynamicforms.Results
				.findByBatchAndForm(batchId, formId);
		if (results == null)
			return notFound(views.html.notFound.render());
		return TODO;
	}
}