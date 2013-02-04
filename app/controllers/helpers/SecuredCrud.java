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
			// TODO Auto-generated method stub
			return super.create();
		}
		return forbidden();
	}

	@Override
	@Transactional(readOnly = true)
	public Result edit(Long id) {
		if (Secured.isAdmin())
			return super.edit(id);
		return forbidden();
	}

	@Override
	@Transactional(readOnly = true)
	public Result fresh() {
		if (Secured.isAdmin())
			return super.fresh();
		return forbidden();
	}

	@Override
	@Transactional(readOnly = true)
	public Result page(int pageNumber, String order, String by) {
		if (Secured.isAdmin())
			return super.page(pageNumber, order, by);
		return forbidden();
	}

	@Override
	@Transactional(readOnly = true)
	public Result show(Long id) {
		if (Secured.isAdmin())
			return super.show(id);
		return forbidden();
	}

	@Override
	@Transactional
	public Result update(Long id) {
		String username = ctx().request().username();
		User user = User.findByEmail(username);
		if (Secured.isAdmin(user)) {
			// TODO Auto-generated method stub
			return super.update(id);
		}
		return forbidden();
	}
}
