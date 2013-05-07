package controllers;

import models.User;
import play.db.jpa.Transactional;
import play.mvc.Call;
import play.mvc.Result;
import play.mvc.With;
import utils.Helper;
import views.html.users.*;
import controllers.helpers.Crud;
import controllers.shib.Session;

@With(Session.class)
public class Users extends Crud<User> {
	public Users() {
		super(User.dao, null, null, page.ref(), show.ref(), null);
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