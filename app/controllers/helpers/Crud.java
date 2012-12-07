package controllers.helpers;

import models.helpers.Model;
import models.helpers.Page;

import play.*;
import play.i18n.Messages;
import play.mvc.*;
import play.api.mvc.Call;
import play.api.templates.*;
import play.data.*;
import static play.data.Form.*;
import play.db.jpa.*;

public class Crud<T extends Model> extends Controller implements CrudInterface {
	private final Class<T> clazz;
	private final Form<T> FORM;
	private final models.helpers.Crud<T, Long> CRUD;
	private final Template2<Long, Form<T>, Html> EDIT;
	private final Template1<Form<T>, Html> FRESH;
	private final Template1<Page<T>, Html> PAGE;
	private final Call REDIRECT;
	private final Template1<T, Html> SHOW;

	Result notFound = notFound(views.html.error.render(
			Messages.get("http.404"), Messages.get("http.404.description")));

	public Crud(Class<T> clazz, models.helpers.Crud<T, Long> CRUD,
			Template2<Long, Form<T>, Html> EDIT,
			Template1<Form<T>, Html> FRESH, Template1<Page<T>, Html> PAGE,
			Call REDIRECT, Template1<T, Html> SHOW) {
		this.clazz = clazz;
		this.FORM = form(this.clazz);
		this.CRUD = CRUD;
		this.EDIT = EDIT;
		this.FRESH = FRESH;
		this.PAGE = PAGE;
		this.REDIRECT = REDIRECT;
		this.SHOW = SHOW;
	}

	@Override
	@Transactional
	public Result create() {
		Form<T> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", Messages.get("crud.cancel"));
			return redirect(REDIRECT);
		} else if (!filledForm.hasErrors()) {
			T t = filledForm.get();
			if (CRUD.create(t)) {
				flash("success", Messages.get("crud.success"));
				return redirect(REDIRECT);
			}
		}
		flash("error", Messages.get("crud.fail"));
		return badRequest(FRESH.render(filledForm));
	}

	@Override
	@Transactional
	public Result edit(Long id) {
		T t = CRUD.findById(id);
		if (t == null)
			return notFound();
		Form<T> filledForm = FORM.fill(t);
		return ok(EDIT.render(id, filledForm));
	}

	@Override
	@Transactional(readOnly = true)
	public Result fresh() {
		return ok(FRESH.render(FORM));
	}

	@Override
	@Transactional(readOnly = true)
	public Result page(int index) {
		return ok(PAGE.render(CRUD.page(index)));
	}

	@Override
	@Transactional(readOnly = true)
	public Result show(Long id) {
		T t = CRUD.findById(id);
		if (t == null)
			return notFound();
		return ok(SHOW.render(t));
	}

	@Override
	@Transactional(readOnly = true)
	public Result update(Long id) {
		if (!CRUD.exists(id))
			return notFound();
		Form<T> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", Messages.get("crud.cancel"));
			return redirect(REDIRECT);
		} else if (!filledForm.hasErrors()) {
			T fresh = filledForm.get();
			fresh.id = id;
			if (CRUD.update(fresh)) {
				flash("success", Messages.get("crud.success"));
				return redirect(REDIRECT);
			}
		}
		flash("error", Messages.get("crud.fail"));
		return badRequest(EDIT.render(id, filledForm));
	}
}
