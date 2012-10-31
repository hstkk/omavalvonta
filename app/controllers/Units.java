package controllers;

import models.Term;
import models.TermCategory;
import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class Units extends Controller {

	final static Form<forms.Term> FORM = form(forms.Term.class);
	final static TermCategory CATEGORY = TermCategory.UNIT;

	public static Result create() {
		return ok(views.html.units.manage.render(FORM));
	}

	@Transactional(readOnly = true)
	public static Result index() {
		return page(1);
	}

	@Transactional(readOnly = true)
	public static Result page(int index) {
		return ok(views.html.units.page.render(Term.page(CATEGORY, index)));
	}

	@Transactional(readOnly = true)
	public static Result update(Long unitId) {
		Term term = Term.findById(unitId);
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
