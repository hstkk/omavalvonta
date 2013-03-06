package controllers.helpers;

import controllers.shib.Secured;
import models.User;
import models.helpers.Page;
import models.helpers.UserModel;
import play.api.mvc.Call;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import utils.Helper;

@Security.Authenticated(Secured.class)
public class SecuredCrud<T extends UserModel> extends Crud<T> {
	public SecuredCrud(models.helpers.Crud<T, Long> CRUD, Form<T> FORM,
			Template2<Long, Form<T>, Html> UPDATE,
			Template1<Form<T>, Html> CREATE, Template1<Page<T>, Html> PAGE,
			Template1<T, Html> SHOW, Call REDIRECT) {
		super(CRUD, FORM, UPDATE, CREATE, PAGE, SHOW, REDIRECT);
	}

	@Override
	@Transactional
	public Result create() {
		String username = ctx().request().username();
		User user = User.findByEmail(username);
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

	@Override
	@Transactional(readOnly = true)
	public Result page(int pageNumber) {
		if (Secured.isAdmin())
			return super.page(pageNumber);
		return Helper.getUnauthorized();
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
		String username = ctx().request().username();
		User user = User.findByEmail(username);
		if (Secured.isAdmin(user)) {
			setUser(user);
			return super.update(id);
		}
		return Helper.getUnauthorized();
	}

	@Override
	protected Result onCreate(T t) {
		t.user = getUser();
		return super.onCreate(t);
	}

	@Override
	protected Result onUpdate(T t, Long id) {
		t.user = getUser();
		return super.onUpdate(t, id);
	}

	private User getUser() {
		User user = (User) ctx().args.get("user");
		ctx().args.remove("user");
		return user;
	}

	private void setUser(User user) {
		ctx().args.put("user", user);
	}
}
