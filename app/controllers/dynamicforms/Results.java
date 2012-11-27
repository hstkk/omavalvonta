package controllers.dynamicforms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.db.jpa.*;

import controllers.Batches;
import controllers.routes;

import forms.dynamicforms.Dynamic;
import forms.dynamicforms.Fieldset;

import models.Batch;
import models.Product;
import models.dynamicforms.Field;

import views.html.*;

//import util.pdf.PDF;

public class Results extends  Controller {

	final static Form<Dynamic> FORM = form(Dynamic.class);

	@Transactional(readOnly = true)
	public static Result index() {
		return ok(views.html.dynamicforms.results.index.render(Product.page(0)));
	}

	@Transactional(readOnly = true)
	public static Result page(Long productId, int index) {
		Product product = Product.findById(productId);
		if (product == null)
			return notFound();
		return ok(views.html.dynamicforms.results.page.render(product,
				models.dynamicforms.Results.page(index)));
	}

	@Transactional(readOnly = true)
	public static Result create(Long productId, Long formId) {
		Product product = Product.findById(productId);
		models.dynamicforms.Form f = models.dynamicforms.Form.findById(formId);
		if (product == null || f == null)
			return notFound();
		String html = f.html;
		return ok(views.html.dynamicforms.results.manage.render(FORM, product,
				f, html));
	}

	@Transactional(readOnly = true)
	public static Result update(Long productId, Long resultsId) {
		Product product = Product.findById(productId);
		if (product == null)
			return notFound();
		models.dynamicforms.Results results = models.dynamicforms.Results
				.findById(resultsId);
		if (results == null)
			return notFound();
		List<models.dynamicforms.Result> r = new ArrayList<models.dynamicforms.Result>(
				results.results);
		r.addAll(Field.headerify(results.form));
		Collections.sort(r);
		String html = utils.Form.formify(r);
		if (html == null || html.equals(""))
			return notFound();
		return ok(views.html.dynamicforms.results.manage.render(
				FORM.fill(new forms.dynamicforms.Dynamic(resultsId)), product,
				results.form, html));
	}

	@Transactional
	public static Result save(Long productId, Long formId) {
		Product product = Product.findById(productId);
		if (product == null)
			return notFound();
		models.dynamicforms.Form f = models.dynamicforms.Form.findById(formId);
		if (f == null)
			return notFound();
		Form<Dynamic> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Tuloksen tallennus peruutettu!");
			return page(productId, 0);
		}
		if (!filledForm.hasErrors()) {
			Dynamic dynamic = filledForm.get();
			models.dynamicforms.Results results = new models.dynamicforms.Results(
					product, dynamic, f);
			if (((results.id != null && results.update()) || (results.id == null && results
					.save()))) {
				flash("status", "Lomake on tallennettu onnistuneesti!");

				/*
				 * List<Long> ids = new ArrayList<Long>(); for(Fieldset
				 * fieldset: dynamic.values) if(fieldset.ack)
				 * ids.add(fieldset.id); if(!ids.isEmpty()) return
				 * ok(views.html.dynamicforms.results.ack.render(ids));
				 */

				return redirect(controllers.dynamicforms.routes.Results.read(
						productId, results.id));
			}
		}
		flash("status", "Lomakkeen tallennus ei onnistunut!");
		return badRequest(views.html.dynamicforms.results.manage.render(
				filledForm, product, f, f.html));
	}

	@Transactional(readOnly = true)
	public static Result read(Long productId, Long resultsId) {
		Product product = Product.findById(productId);
		if (product == null)
			return notFound();
		models.dynamicforms.Results results = models.dynamicforms.Results
				.findById(resultsId);
		if (results == null)
			return notFound();
		List<models.dynamicforms.Result> r = new ArrayList<models.dynamicforms.Result>(
				results.results);
		r.addAll(Field.headerify(results.form));
		Collections.sort(r);
		List<Batch> batches = null;
		return ok(views.html.dynamicforms.results.read.render(product, results,
				r, batches));
	}

	@Transactional(readOnly = true)
	public static Result history(Long productId, Long resultsId) {
		Product product = Product.findById(productId);
		if (product == null)
			return notFound();
		models.dynamicforms.Results results = models.dynamicforms.Results
				.findById(resultsId);
		if (results == null)
			return notFound();
		Map<Field, List<models.dynamicforms.Result>> history = results
				.getHistory();
		return ok(views.html.dynamicforms.results.history.render(product,
				results, history));
	}

	/*@Transactional(readOnly = true)
	public static Result pdfify(Long productId, Long resultsId) {
		Product product = Product.findById(productId);
		if (product == null)
			return notFound();
		models.dynamicforms.Results results = models.dynamicforms.Results
				.findById(resultsId);
		if (results == null)
			return notFound();
		List<models.dynamicforms.Result> r = new ArrayList<models.dynamicforms.Result>(
				results.results);
		r.addAll(Field.headerify(results.form));
		Collections.sort(r);
		return ok(
				PDF.toBytes(views.html.dynamicforms.results.pdfify.render(
						product, results, r))).as("application/pdf");
	}*/
}