package controllers;

import models.Batch;
import models.Product;
import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class Batches extends Controller {

	final static Form<models.Batch> batchForm = form(models.Batch.class);
	
	@Transactional(readOnly = true)
	public static Result index() {
		return page(1);
	}

	@Transactional(readOnly = true)
	public static Result page(int page) {
		return ok(views.html.batches.all.render(models.Batch.findUnready(),
				models.Batch.findReady()));
	}

	@Transactional(readOnly = true)
	public static Result add() {
		return ok(views.html.batches.add.render(batchForm));
	}

	@Transactional
	public static Result save() {
		Form<models.Batch>  filledbatchForm= batchForm.bindFromRequest();
		if (filledbatchForm.field("action").value().equals("peruuta")) {
			flash("status", "Erän luonti peruutettu!");
			return redirect(routes.Batches.index());
		} else if (!filledbatchForm.hasErrors()) {
			Batch batch = filledbatchForm.get();
			if (batch.save()) {
				flash("status", "Erä on luotu onnistuneesti!");
				return redirect(routes.Batches.show(batch.id));
			}
		}
		flash("status", "Erän luonti ei onnistunut!");
		return badRequest(views.html.batches.add.render(filledbatchForm));
	}

	@Transactional(readOnly = true)
	public static Result show(Long batchId) {
		return TODO;
	}
}
