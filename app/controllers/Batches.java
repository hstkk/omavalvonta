package controllers;

import java.util.List;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.db.jpa.*;

import models.Batch;
import models.dynamicforms.Results;

import views.html.*;

public class Batches extends Controller {

	final static Form<forms.Batch> FORM = form(forms.Batch.class);

	@Transactional(readOnly = true)
	public static Result create() {
		return ok(views.html.batches.manage.render(FORM));
	}

	@Transactional(readOnly = true)
	public static Result index() {
		return page(1);
	}

	@Transactional(readOnly = true)
	public static Result page(int index) {
		return ok(views.html.batches.page.render(Batch.page(index)));
	}

	@Transactional
	public static Result save() {
		Form<forms.Batch> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Erän tallennus peruutettu!");
			return redirect(routes.Batches.index());
		} else if (!filledForm.hasErrors()) {
			Batch batch = new Batch(filledForm.get());
			// TODO smarter save/update
			if (batch.save()) {
				flash("success", "Erä on tallennettu onnistuneesti!");
				return redirect(routes.Batches.index());
			}
		}
		flash("error", "Erän tallennus ei onnistunut!");
		return badRequest(views.html.batches.manage.render(filledForm));
	}

	@Transactional(readOnly = true)
	public static Result read(Long batchId) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound(views.html.notFound.render());
		List<Results> results = models.dynamicforms.Results.findByBatch(batch);
		return ok(views.html.batches.read.render(batch, results));
	}
}
