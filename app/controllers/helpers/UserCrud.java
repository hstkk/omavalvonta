package controllers.helpers;

import models.User;
import models.helpers.Page;
import models.helpers.UserModel;
import play.api.mvc.Call;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import utils.Helper;
import controllers.shib.Secured;
import controllers.shib.SessionTimeout;
import controllers.shib.Shibboleth;

public class UserCrud<T extends UserModel> extends Crud<T> {
	public UserCrud(models.helpers.Crud<T, Long> CRUD, Form<T> FORM,
			Template2<Long, Form<T>, Html> UPDATE,
			Template1<Form<T>, Html> CREATE, Template1<Page<T>, Html> PAGE,
			Template1<T, Html> SHOW, Call REDIRECT) {
		super(CRUD, FORM, UPDATE, CREATE, PAGE, SHOW, REDIRECT);
	}

	@Security.Authenticated(Secured.class)
	@With(SessionTimeout.class)
	public Result ack(Long id) {
		User user = getUser();
		if (user != null) {
			T t = CRUD.findById(id);
			if (REDIRECT == null || t == null)
				return notFound();
			if (t.user != null)
				return Helper.getInternalServerError();
			t.user = user;
			if (CRUD.update(t))
				flash("success", Messages.get("crud.success"));
			else
				flash("error", Messages.get("crud.fail"));
			return redirect(REDIRECT);
		}
		return Helper.getUnauthorized();
	}

	@Override
	@Transactional
	public Result create() {
		return super.create();
	}

	@Override
	@Transactional(readOnly = true)
	public Result edit(Long id) {
		return super.edit(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Result fresh() {
		return super.fresh();
	}

	private User getUser() {
		String username = ctx().request().username();
		return User.findByEmail(username);
	}

	@Override
	protected Result onCreate(T t) {
		User user = getUser();
		t.user = user;
		Result result = super.onCreate(t);
		// TODO returnUrl
		String returnUrl = "";
		if (user == null && result != null)
			return Shibboleth.login(returnUrl);
		return result;
	}

	@Override
	protected Result onUpdate(T t, Long id) {
		User user = getUser();
		t.user = user;
		Result result = super.onUpdate(t, id);
		// TODO returnUrl
		String returnUrl = "";
		if (user == null && result != null)
			return Shibboleth.login(returnUrl);
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Result page(int pageNumber) {
		return super.page(pageNumber);
	}

	@Override
	@Transactional(readOnly = true)
	public Result show(Long id) {
		return super.show(id);
	}

	@Override
	@Transactional
	public Result update(Long id) {
		return super.update(id);
	}
}
