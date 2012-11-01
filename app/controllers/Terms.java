package controllers;

import models.Term;
import models.TermCategory;
import models.helpers.Page;
import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class Terms extends Controller{

	final static Form<forms.Term> FORM = form(forms.Term.class);
	final static TermCategory CATEGORY = TermCategory.UNIT;

	public static Result create() {
		return ok(views.html.terms.manage.render(FORM));
	}

	@Transactional(readOnly = true)
	public static Result read(Long id) {
		Term term = Term.crud.read(id);
		if (term == null)
			return notFound(views.html.notFound.render());
		return ok(views.html.terms.manage.render(FORM.fill(new forms.Term(term))));
	}

	@Transactional
	public static Result update(Long id) {
		Term term = Term.crud.read(id);
		if (term == null)
			return notFound(views.html.notFound.render());
		Form<forms.Term> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Tallennus peruutettu!");
			return redirect(routes.Terms.index());
		} else if (!filledForm.hasErrors()) {
			term.set(filledForm.get());
			if (Term.crud.update(term)) {
				flash("success", "Tallennus onnistui!");
				return redirect(routes.Terms.index());
			}
		}
		flash("error", "Tallennus epäonnistui!");
		return badRequest(views.html.terms.manage.render(filledForm));
	}

	@Transactional
	public static Result delete(Long id) {
		Term term = Term.crud.read(id);
		if (term == null)
			return notFound(views.html.notFound.render());
		Term.crud.delete(term);
		return redirect(routes.Terms.index());
	}

	@Transactional
	public static Result save() {
		Form<forms.Term> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Tallennus peruutettu!");
			return redirect(routes.Terms.index());
		} else if (!filledForm.hasErrors()) {
			Term term = new Term(filledForm.get(), CATEGORY);
			if (Term.crud.create(term)) {
				flash("success", "Tallennus onnistui!");
				return redirect(routes.Terms.index());
			}
		}
		flash("error", "Tallennus epäonnistui!");
		return badRequest(views.html.terms.manage.render(filledForm));
	}

	@Transactional(readOnly = true)
	public static Result index() {
		return page(1);
	}

	@Transactional(readOnly = true)
	public static Result page(int index) {
		Page<Term> page = Term.page(index, CATEGORY);
		return ok(views.html.terms.page.render(page));
	}
}
