package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.db.jpa.*;

import models.Term;
import models.TermCategory;
import models.helpers.Page;

import views.html.*;

public class Terms extends Controller{

	final static Form<Term> FORM = form(Term.class);

	public static Result create() {
		return ok(views.html.terms.manage.render(FORM));
	}

	/*@Transactional(readOnly = true)
	public static Result read(Long id) {
		Term term = Term.crud.read(id);
		if (term == null)
			return notFound();
		return ok(views.html.terms.manage.render(FORM.fill(new forms.Term(term))));
	}

	@Transactional
	public static Result update(Long id) {
		Term term = Term.crud.read(id);
		if (term == null)
			return notFound();
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
			return notFound();
		Term.crud.delete(term);
		return redirect(routes.Terms.index());
	}*/

	@Transactional
	public static Result save() {
		Form<Term> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Tallennus peruutettu!");
			return redirect(routes.Terms.index());
		} else if (!filledForm.hasErrors()) {
			if (Term.crud.create(filledForm.get())) {
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
		Page<Term> page = Term.page(index);
		return ok(views.html.terms.page.render(page));
	}
}
