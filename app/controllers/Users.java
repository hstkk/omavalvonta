package controllers;

import models.User;
import play.api.templates.Template1;
import play.api.templates.Template2;
import static play.data.Form.*;
import play.db.jpa.Transactional;
import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.users.*;
import controllers.helpers.Crud;

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