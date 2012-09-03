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
	public static Result create(Long productId, Long formId) {
		Product product = Product.findById(productId);
		models.dynamicforms.Form f = models.dynamicforms.Form.findById(formId);
		if (product == null || f == null)
			return notFound(views.html.notFound.render());
		String html = f.html;
		if (html == null || html.equals(""))
			return notFound(views.html.notFound.render());
		return ok(views.html.dynamicforms.results.manage.render(FORM, product,
				f, html));
	}

	@Transactional(readOnly = true)
	public static Result update(Long productId, Long resultsId) {
		Product product = Product.findById(productId);
		if (product == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Results results = models.dynamicforms.Results
				.findById(resultsId);
		if (results == null)
			return notFound(views.html.notFound.render());
		String html = utils.Form.formify(models.dynamicforms.Result
				.findByResults(results.id));
		if (html == null || html.equals(""))
			return notFound(views.html.notFound.render());
		return ok(views.html.dynamicforms.results.manage.render(FORM, product,
				results.form, html));
	}

	@Transactional
	public static Result save(Long productId, Long formId) {
		Product product = Product.findById(productId);
		if (product == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Form f = models.dynamicforms.Form.findById(formId);
		if (f == null)
			return notFound(views.html.notFound.render());
		Form<Dynamic> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Tuloksen tallennus peruutettu!");
			// TODO
			// return Batches.read(batchId);
		}
		Dynamic dynamic = filledForm.get();
		if (!filledForm.hasErrors()) {
			models.dynamicforms.Results results = new models.dynamicforms.Results(
					dynamic.values, f);
			if (results.save()) {
				flash("status", "Lomake on tallennettu onnistuneesti!");
				return read(productId, results.id);
			}
		}
		flash("status", "Lomakkeen tallennus ei onnistunut!");
		return badRequest(views.html.dynamicforms.results.manage.render(
				filledForm, product, f, utils.Form.formify(dynamic.values)));
	}

	@Transactional(readOnly = true)
	public static Result read(Long productId, Long resultsId) {
		Product product = Product.findById(productId);
		if (product == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Results results = models.dynamicforms.Results
				.findById(resultsId);
		if (results == null)
			return notFound(views.html.notFound.render());
		List<models.dynamicforms.Result> r = models.dynamicforms.Result
				.findByResults(results.id);
		return ok(views.html.dynamicforms.results.read.render(results, r));
	}

	/*@Transactional(readOnly = true)
	public static Result history(Long productId, Long resultsId) {
		Product product = Product.findById(productId);
		if (product == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Results results = models.dynamicforms.Results
				.findById(resultsId);
		if (results == null)
			return notFound(views.html.notFound.render());
		List<models.dynamicforms.Result> r = models.dynamicforms.Result
				.findByResults(results.id);
		return ok(views.html.dynamicforms.results.read.render(results, r));
	}

	@Transactional(readOnly = true)
	public static Result pdfify(Long productId, Long resultsId) {
		Product product = Product.findById(productId);
		if (product == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Results results = models.dynamicforms.Results
				.findById(resultsId);
		if (results == null)
			return notFound(views.html.notFound.render());
		List<models.dynamicforms.Result> r = models.dynamicforms.Result
				.findByResults(results.id);
		return ok(views.html.dynamicforms.results.read.render(results, r));
	}*/
}