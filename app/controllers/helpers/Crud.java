package controllers.helpers;

import models.helpers.Dao;
import models.helpers.Model;
import models.helpers.Page;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.libs.F;
import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import utils.Helper;
import com.google.common.base.Optional;
import controllers.shib.Session;

@With(Session.class)
public class Crud<T extends Model> extends Controller implements CrudInterface {
	protected final F.Option<Form<T>> FORM;
	protected final F.Option<Dao<T, Long>> DAO;
	protected final F.Option<Template1<Form<T>, Html>> CREATE;
	private final F.Option<Template1<Page<T>, Html>> PAGE;
	private final F.Option<Template1<T, Html>> SHOW;
	private final F.Option<Template2<T, Form<T>, Html>> UPDATE;

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
			F.Option<Dao<T, Long>> DAO,
			F.Option<Form<T>> FORM,
			F.Option<Template1<Form<T>, Html>> CREATE,
			F.Option<Template1<Page<T>, Html>> PAGE,
			F.Option<Template1<T, Html>> SHOW,
			F.Option<Template2<T, Form<T>, Html>> UPDATE
		) {
		this.DAO = DAO;
		this.FORM = FORM;
		this.CREATE = CREATE;
		this.PAGE = PAGE;
		this.SHOW = SHOW;
		this.UPDATE = UPDATE;
	}

	protected Form<T> bindForm() {
		return FORM.get().bindFromRequest();
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
		if(CREATE.isEmpty() && FORM.isEmpty())
			return Helper.getNotFound();
		Form<T> filledForm = bindForm();
		
		Optional<Result> result = onCancel(filledForm);

		if (result.isPresent())
			return result.get();
		filledForm = validateForm(filledForm);
		if (!filledForm.hasErrors()) {
			T t = filledForm.get();
			result = onCreateOrUpdate(t);
			if (result.isPresent())
				return result.get();
		}
		flash("error", Messages.get("crud.fail"));
		return badRequest(CREATE.get().render(filledForm));
	}

	@Override
	@Transactional(readOnly = true)
	public Result edit(Long id) {
		if (UPDATE.isEmpty() || DAO.isEmpty() || FORM.isEmpty())
			return Helper.getNotFound();
		Optional<T> t = DAO.get().findById(id);
		Optional<Result> result = isUpdatable(t);
		if (result.isPresent())
			return result.get();
		Form<T> filledForm = FORM.get().fill(t.get());
		return ok(UPDATE.get().render(t.get(), filledForm));
	}

	@Override
	@Transactional(readOnly = true)
	public Result fresh() {
		if (CREATE.isEmpty() || FORM.isEmpty())
			return Helper.getNotFound();
		return ok(CREATE.get().render(FORM.get()));
	}

	public Optional<Result> isUpdatable(Optional<T> t) {
		if (t.isPresent())
			return Optional.fromNullable(Helper.getNotFound());
		return Optional.absent();
	}

	protected Optional<Result> onCancel(Form<T> filledForm) {
		return onCancel(filledForm, Optional.<Long>absent());
	}

	protected Optional<Result> onCancel(Form<T> filledForm, Long id) {
		return onCancel(filledForm, Optional.fromNullable(id));
	}

	protected Optional<Result> onCancel(Form<T> filledForm, Optional<Long> id) {
		if (filledForm.field("action").value()
				.equals(Messages.get("crud.action.cancel"))) {
			flash("warning", Messages.get("crud.cancel"));
			Call call = (id.isPresent()) ? callShow(id.get()) : callPage();
			return Optional.fromNullable((Result) redirect(call));
		}
		return Optional.absent();
	}

	protected Optional<Result> onCreateOrUpdate(T t) {
		return onCreateOrUpdate(t, Optional.<Long>absent());
	}

	protected Optional<Result> onCreateOrUpdate(T t, Long id) {
		return onCreateOrUpdate(t, Optional.fromNullable(id));
	}

	protected Optional<Result> onCreateOrUpdate(T t, Optional<Long> id) {
		if (DAO.isDefined()) {
			boolean success = false;
			if(id.isPresent()) {
				t.id = id.get();
				success = DAO.get().update(t);
			} else {
				success = DAO.get().create(t);
			}
			if (success) {
				flash().remove("error");
				flash("success", Messages.get("crud.success"));
				Call call = callShow(t.id);
				return Optional.fromNullable((Result) redirect(call));
			}
		}
		return Optional.absent();
	}

	@Override
	@Transactional(readOnly = true)
	public Result page(int pageNumber) {
		if (PAGE.isEmpty() || DAO.isEmpty())
			return Helper.getNotFound();
		return ok(PAGE.get().render(DAO.get().page(pageNumber)));
	}

	@Override
	@Transactional(readOnly = true)
	public Result show(Long id) {
		if (SHOW.isEmpty() || DAO.isEmpty())
			return Helper.getNotFound();
		Optional<T> t = DAO.get().findById(id);
		if (!t.isPresent())
			return Helper.getNotFound();
		return ok(SHOW.get().render(t.get()));
	}

	@Override
	@Transactional
	public Result update(Long id) {
		if (UPDATE.isEmpty() || DAO.isEmpty() || DAO.get().doesNotExist(id) || FORM.isEmpty())
			return Helper.getNotFound();
		Form<T> filledForm = bindForm();
		Optional<Result> result = onCancel(filledForm, id);
		if (result.isPresent())
			return result.get();
		filledForm = validateForm(filledForm, id);
		if (!filledForm.hasErrors()) {
			T fresh = filledForm.get();
			result = onCreateOrUpdate(fresh, id);
			if (result.isPresent())
				return result.get();
		}
		Optional<T> t = DAO.get().findById(id);
		flash("error", Messages.get("crud.fail"));
		return badRequest(UPDATE.get().render(t.get(), filledForm));
	}

	protected Form<T> validateForm(Form<T> filledForm) {
		return validateForm(filledForm, Optional.<Long>absent());
	}

	protected Form<T> validateForm(Form<T> filledForm, Long id) {
		return validateForm(filledForm, Optional.fromNullable(id));
	}

	protected Form<T> validateForm(Form<T> filledForm, Optional<Long> id) {
		return filledForm;
	}
}
