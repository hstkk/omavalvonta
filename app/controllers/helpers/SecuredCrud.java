package controllers.helpers;

import models.User;
import models.helpers.Dao;
import models.helpers.Page;
import models.helpers.UserModel;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.F;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import utils.Helper;
import com.google.common.base.Optional;
import controllers.shib.Secured;
import controllers.shib.Session;

@With(Session.class)
@Security.Authenticated(Secured.class)
public class SecuredCrud<T extends UserModel> extends Crud<T> {
	public SecuredCrud(
			F.Option<Dao<T, Long>> DAO,
			F.Option<Form<T>> FORM,
			F.Option<Template1<Form<T>, Html>> CREATE,
			F.Option<Template1<Page<T>, Html>> PAGE,
			F.Option<Template1<T, Html>> SHOW,
			F.Option<Template2<T, Form<T>, Html>> UPDATE
		) {
		super(DAO, FORM, CREATE, PAGE, SHOW, UPDATE);
	}

	@Override
	@Transactional
	public Result create() {
		User user = Session.user();
		if (Secured.isAdmin(user)) {
			setUser(user);
			return super.create();
		}
		return Helper.getUnauthorized();
	}

	@Override
	@Transactional(readOnly = true)
	public Result edit(Long id) {
		if (Secured.isAdmin())
			return super.edit(id);
		return Helper.getUnauthorized();
	}

	@Override
	@Transactional(readOnly = true)
	public Result fresh() {
		if (Secured.isAdmin())
			return super.fresh();
		return Helper.getUnauthorized();
	}

	private User getUser() {
		User user = (User) ctx().args.get("user");
		ctx().args.remove("user");
		return user;
	}

	@Override
	public Optional<Result> onCreateOrUpdate(T t, Optional<Long> id) {
		t.user = getUser();
		return super.onCreateOrUpdate(t, id);
	}

	@Override
	@Transactional(readOnly = true)
	public Result page(int pageNumber) {
		if (Secured.isAdmin())
			return super.page(pageNumber);
		return Helper.getUnauthorized();
	}

	private void setUser(User user) {
		ctx().args.put("user", user);
	}

	@Override
	@Transactional(readOnly = true)
	public Result show(Long id) {
		if (Secured.isAdmin())
			return super.show(id);
		return Helper.getUnauthorized();
	}

	@Override
	@Transactional
	public Result update(Long id) {
		User user = Session.user();
		if (Secured.isAdmin(user)) {
			setUser(user);
			return super.update(id);
		}
		return Helper.getUnauthorized();
	}
}
