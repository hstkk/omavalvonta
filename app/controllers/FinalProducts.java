package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.db.jpa.*;

import models.Batch;
import models.FinalProduct;
import models.Term;
import models.helpers.Page;

import views.html.*;

public class FinalProducts extends Controller {

	final static Form<FinalProduct> FORM = form(FinalProduct.class);

	@Transactional(readOnly = true)
	public static Result index() {
		return page(1);
	}

	@Transactional(readOnly = true)
	public static Result page(int index) {
		Page<FinalProduct> page = FinalProduct.page(index);
		return ok(views.html.finalproducts.page.render(page));
	}

	@Transactional(readOnly = true)
	public static Result read(Long batchId) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound();
		FinalProduct result = FinalProduct.findByBatch(batch);
		if (result == null)
			return redirect(routes.FinalProducts.edit(batchId));
		return ok(views.html.finalproducts.read.render(result));
	}

	@Transactional()
	public static Result save(Long batchId) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound();
		Form<models.FinalProduct> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Tallennus peruutettu!");
			return redirect(routes.FinalProducts.read(batchId));
		} else if (!filledForm.hasErrors()) {
			if (FinalProduct.crud.createOrUpdate(filledForm.get())) {
				flash("success", "Tallennus onnistui!");
				return redirect(routes.FinalProducts.read(batchId));
			}
		}
		flash("error", "Tallennus epäonnistui!");
		return ok(views.html.finalproducts.form.render(filledForm));
	}

	@Transactional(readOnly = true)
	public static Result edit(Long batchId) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound();
		FinalProduct result = FinalProduct.findByBatch(batch);
		return ok(views.html.finalproducts.form.render(FORM.fill(result)));
	}
}
