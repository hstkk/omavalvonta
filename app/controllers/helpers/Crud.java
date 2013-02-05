package controllers.helpers;

import controllers.shib.SessionTimeout;
import models.helpers.Model;
import models.helpers.Page;

import play.i18n.Messages;
import play.mvc.*;
import play.api.mvc.Call;
import play.api.templates.*;
import play.data.*;
import play.db.jpa.*;

@With(SessionTimeout.class)
public class Crud<T extends Model> extends Controller implements CrudInterface {
	private final Form<T> FORM;
	private final models.helpers.Crud<T, Long> CRUD;
	private final Template1<Form<T>, Html> CREATE;
	private final Template1<Page<T>, Html> PAGE;
	private final Template1<T, Html> SHOW;
	private final Template2<Long, Form<T>, Html> UPDATE;
	private final Call REDIRECT;

	/**
	 * 
	 * @param CRUD
	 * @param FORM
	 * @param UPDATE
	 * @param CREATE
	 * @param PAGE
	 * @param SHOW
	 * @param REDIRECT
	 */
	public Crud(models.helpers.Crud<T, Long> CRUD, Form<T> FORM,
			Template2<Long, Form<T>, Html> UPDATE,
			Template1<Form<T>, Html> CREATE, Template1<Page<T>, Html> PAGE,
			Template1<T, Html> SHOW, Call REDIRECT) {
		this.CRUD = CRUD;
		this.FORM = FORM;
		this.UPDATE = UPDATE;
		this.CREATE = CREATE;
		this.PAGE = PAGE;
		this.SHOW = SHOW;
		this.REDIRECT = REDIRECT;
	}

	@Override
	@Transactional
	public Result create() {
		if (REDIRECT == null || CREATE == null)
			return notFound();
		Form<T> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value()
				.equals(Messages.get("crud.action.cancel"))) {
			flash("warning", Messages.get("crud.cancel"));
			return redirect(REDIRECT);
		} else if (!filledForm.hasErrors()) {
			T t = filledForm.get();
			Result result = onCreate(t);
			if (result != null)
				return result;
		}
		flash("error", Messages.get("crud.fail"));
		return badRequest(CREATE.render(filledForm));
	}

	@Override
	@Transactional(readOnly = true)
	public Result edit(Long id) {
		if (UPDATE == null)
			return notFound();
		T t = CRUD.findById(id);
		if (t == null)
			return notFound();
		Form<T> filledForm = FORM.fill(t);
		return ok(UPDATE.render(id, filledForm));
	}

	@Override
	@Transactional(readOnly = true)
	public Result fresh() {
		if (CREATE == null)
			return notFound();
		return ok(CREATE.render(FORM));
	}

	@Override
	@Transactional(readOnly = true)
	public Result page(int pageNumber) {
		if (PAGE == null)
			return notFound();
		return ok(PAGE.render(CRUD.page(pageNumber)));
	}

	@Override
	@Transactional(readOnly = true)
	public Result show(Long id) {
		if (SHOW == null)
			return notFound();
		T t = CRUD.findById(id);
		if (t == null)
			return notFound();
		return ok(SHOW.render(t));
	}

	@Override
	@Transactional
	public Result update(Long id) {
		if (REDIRECT == null || UPDATE == null || !CRUD.exists(id))
			return notFound();
		Form<T> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value()
				.equals(Messages.get("crud.action.cancel"))) {
			flash("warning", Messages.get("crud.cancel"));
			return redirect(REDIRECT);
		} else if (!filledForm.hasErrors()) {
			T fresh = filledForm.get();
			Result result = onUpdate(fresh, id);
			if (result != null)
				return result;
		}
		flash("error", Messages.get("crud.fail"));
		return badRequest(UPDATE.render(id, filledForm));
	}

	protected Result onCreate(T t) {
		if (CRUD.create(t)) {
			flash("success", Messages.get("crud.success"));
			return redirect(REDIRECT);
		}
		return null;
	}

	protected Result onUpdate(T t, Long id) {
		t.id = id;
		if (CRUD.update(t)) {
			flash("success", Messages.get("crud.success"));
			return redirect(REDIRECT);
		}
		return null;
	}
}
