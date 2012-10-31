package controllers.helpers;

import java.io.Serializable;

import models.Term;
import models.TermCategory;

import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

public class Crud<T, E, ID extends Serializable> extends Controller {

	private final models.helpers.Crud<T, ID> crud;
	private final Form<E> FORM;
	private final TermCategory CATEGORY;

	public Crud(models.helpers.Crud<T, ID> crud, Form<E> _form,
			TermCategory CATEGORY) {
		this.crud = crud;
		this.FORM = _form;
		this.CATEGORY = CATEGORY;
	}

	public Result create() {
		return ok(views.html.terms.manage.render(FORM, CATEGORY));
	}

	@Transactional(readOnly = true)
	public Result read(ID id) {
		T value = crud.read(id);
		if (value == null)
			return notFound(views.html.notFound.render());
		return ok(views.html.terms.manage.render(FORM.fill(value), CATEGORY));
	}

	@Transactional
	public Result update(ID id) {
		T value = crud.read(id);
		if (value == null)
			return notFound(views.html.notFound.render());
		Form<E> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Tallennus peruutettu!");
			return redirect(routes.Units.index());
		} else if (!filledForm.hasErrors()) {
			E value = filledForm.get();
			if (crud.update(value)) {
				flash("success", "Tallennus onnistui!");
				//return redirect(routes.Terms.index());
				return index();
			}
		}
		flash("error", "Tallennus epäonnistui!");
		return badRequest(views.html.terms.manage.render(filledForm), CATEGORY);
	}

	@Transactional
	public Result delete(ID id) {
		T value = crud.read(id);
		if (value == null)
			return notFound(views.html.notFound.render());
		crud.delete(value);
		return index();
	}

	@Transactional
	public Result save() {
		Form<E> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Tallennus peruutettu!");
			//return redirect(routes.Units.index());
			return index();
		} else if (!filledForm.hasErrors()) {
			E value = filledForm.get();
			// TODO smarter save/update
			if (crud.create(value)) {
				flash("success", "Tallennus onnistui!");
				//return redirect(routes.Terms.index());
				return index();
			}
		}
		flash("error", "Tallennus epäonnistui!");
		return badRequest(views.html.terms.manage.render(filledForm));
	}

	@Transactional(readOnly = true)
	public Result index() {
		return page(1);
	}

	@Transactional(readOnly = true)
	public Result page(int index) {
		return ok(views.html.units.page.render(Term.page(CATEGORY, index)));
	}
}
