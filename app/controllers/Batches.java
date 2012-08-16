package controllers;

import java.util.List;

import models.Batch;
import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class Batches extends Controller {

	final static Form<Batch> FORM = form(Batch.class);

	public static Result create() {
		return views.html.batches.manage.render(FORM);
	}

	public static Result index() {
		return page(1);
	}

	@Transactional(readOnly = true)
	public static Result page(int index) {
		return views.html.batches.page.render(Batch.page(index));
	}

	@Transactional
	public static Result save() {
		Form<Batch> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Erän tallennus peruutettu!");
			return redirect(routes.Batches.index());
		} else if (!filledForm.hasErrors()) {
			Batch batch = filledForm.get();
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
		return views.html.batches.read.render(batch);
	}
}
