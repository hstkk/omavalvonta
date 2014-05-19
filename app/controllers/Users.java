package controllers;

import models.User;
import models.helpers.Dao;
import models.helpers.Page;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.F;
import play.mvc.Call;
import play.mvc.Result;
import play.mvc.With;
import utils.Helper;
import views.html.users.page;
import views.html.users.show;
import controllers.helpers.Crud;
import controllers.shib.Session;

@With(Session.class)
public class Users extends Crud<User> {
	public Users() {
		super(
				F.Option.<Dao<User, Long>>Some(User.dao),
				F.Option.<Form<User>>None(),
				F.Option.<Template1<Form<User>, Html>>None(),
				F.Option.<Template1<Page<User>, Html>>Some(page.ref()),
				F.Option.<Template1<User, Html>>Some(show.ref()),
				F.Option.<Template2<User, Form<User>, Html>>None()
			);
	}

	@Override
	public Call callPage() {
		return controllers.routes.Users.page(1);
	}

	@Override
	public Call callShow(Long id) {
		return controllers.routes.Users.show(id);
	}

	@Transactional(readOnly = true)
	public Result home() {
		User user = Session.user();
		if (user != null)
			return show(user.id);
		return Helper.getNotFound();
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
}