package controllers;

import models.Unit;
import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class Units extends Controller {

	final static Form<Unit> FORM = form(Unit.class);

	public static Result create() {
		return ok(views.html.units.manage.render(FORM));
	}

	public static Result index() {
		return page(1);
	}

	@Transactional(readOnly = true)
	public static Result page(int index) {
		return ok(views.html.units.page.render(Unit.page(index)));
	}

	@Transactional(readOnly = true)
	public static Result update(Long unitId) {
		Unit unit = Unit.findById(unitId);
		if (unit == null)
			return notFound(views.html.notFound.render());
		return ok(views.html.units.manage.render(FORM.fill(unit)));
	}

	@Transactional
	public static Result save() {
		Form<Unit> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Mittayksikön tallennus peruutettu!");
			return redirect(routes.Units.index());
		} else if (!filledForm.hasErrors()) {
			Unit unit = filledForm.get();
			// TODO smarter save/update
			if ((unit.id != null && unit.update())
					|| (unit.id == null && unit.save())) {
				flash("success", "Mittayksikkö on tallennettu onnistuneesti!");
				return redirect(routes.Units.index());
			}
		}
		flash("error", "Mittayksikön tallennus ei onnistunut!");
		return badRequest(views.html.units.manage.render(filledForm));
	}
}
