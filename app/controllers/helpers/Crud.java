package controllers.helpers;

import models.helpers.Dao;
import models.helpers.Model;
import models.helpers.Page;
import play.mvc.Call;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import utils.Helper;
import controllers.shib.Session;

@With(Session.class)
public class Crud<T extends Model> extends Controller implements CrudInterface {
	protected final Form<T> FORM;
	protected final Dao<T, Long> DAO;
	private final Template1<Form<T>, Html> CREATE;
	private final Template1<Page<T>, Html> PAGE;
	private final Template1<T, Html> SHOW;
	private final Template2<Long, Form<T>, Html> UPDATE;

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
	public Crud(
			models.helpers.Dao<T, Long> DAO,
			Form<T> FORM,
			Template1<Form<T>, Html> CREATE,
			Template1<Page<T>, Html> PAGE,
			Template1<T, Html> SHOW,
			Template2<Long, Form<T>, Html> UPDATE
		) {
		this.DAO = DAO;
		this.FORM = FORM;
		this.CREATE = CREATE;
		this.PAGE = PAGE;
		this.SHOW = SHOW;
		this.UPDATE = UPDATE;
	}

	protected Form<T> bindForm() {
		return FORM.bindFromRequest();
	}

	@Override
	public Call callPage() {
		return controllers.routes.Application.index(0);
	}

	@Override
	public Call callShow(Long id) {
		return callPage();
	}

	@Override
	@Transactional
	public Result create() {
		if (CREATE == null || FORM == null)
			return Helper.getNotFound();
		Form<T> filledForm = bindForm();
		Result result = onCancel(filledForm);
		if (result != null)
			return result;
		if (!filledForm.hasErrors()) {
			T t = filledForm.get();
			result = onCreateOrUpdate(t);
			if (result != null)
				return result;
		}
		flash("error", Messages.get("crud.fail"));
		return badRequest(CREATE.render(filledForm));
	}

	@Override
	@Transactional(readOnly = true)
	public Result edit(Long id) {
		if (UPDATE == null || DAO == null || FORM == null)
			return Helper.getNotFound();
		T t = DAO.findById(id);
		if (t == null)
			return Helper.getNotFound();
		Form<T> filledForm = FORM.fill(t);
		return ok(UPDATE.render(id, filledForm));
	}

	@Override
	@Transactional(readOnly = true)
	public Result fresh() {
		if (CREATE == null || FORM == null)
			return Helper.getNotFound();
		return ok(CREATE.render(FORM));
	}

	protected Result onCancel(Form<T> filledForm) {
		return onCancel(filledForm, null);
	}

	protected Result onCancel(Form<T> filledForm, Long id) {
		if (filledForm.field("action").value()
				.equals(Messages.get("crud.action.cancel"))) {
			flash("warning", Messages.get("crud.cancel"));
			Call call = (id != null) ? callShow(id) : callPage();
			return redirect(call);
		}
		return null;
	}

	protected Result onCreateOrUpdate(T t) {
		return onCreateOrUpdate(t, null);
	}

	public Result onCreateOrUpdate(T t, Long id) {
		if (DAO != null) {
			boolean success = false;
			if (id == null)
				success = DAO.create(t);
			else {
				t.id = id;
				success = DAO.update(t);
			}
			if (success) {
				flash("success", Messages.get("crud.success"));
				Call call = callShow(t.id);
				return redirect(call);
			}
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Result page(int pageNumber) {
		if (PAGE == null || DAO == null)
			return Helper.getNotFound();
		return ok(PAGE.render(DAO.page(pageNumber)));
	}

	@Override
	@Transactional(readOnly = true)
	public Result show(Long id) {
		if (SHOW == null || DAO == null)
			return Helper.getNotFound();
		T t = DAO.findById(id);
		if (t == null)
			return Helper.getNotFound();
		return ok(SHOW.render(t));
	}

	@Override
	@Transactional
	public Result update(Long id) {
		if (UPDATE == null || DAO == null || !DAO.exists(id) || FORM == null)
			return Helper.getNotFound();
		Form<T> filledForm = FORM.bindFromRequest();
		Result result = onCancel(filledForm, id);
		if (result != null)
			return result;
		if (!filledForm.hasErrors()) {
			T fresh = filledForm.get();
			result = onCreateOrUpdate(fresh, id);
			if (result != null)
				return result;
		}
		flash("error", Messages.get("crud.fail"));
		return badRequest(UPDATE.render(id, filledForm));
	}
}
