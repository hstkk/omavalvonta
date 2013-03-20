package controllers;

import models.User;
import play.mvc.Call;
import play.mvc.Controller;
import views.html.users.*;
import controllers.helpers.Crud;

public class Users extends Controller {
	public final static Router router = new Router();

	public final static Crud<User> crud = new Crud<User>(
			User.crud,
			null,
			router,
			null,
			page.ref(),
			show.ref(),
			null
		);

	public static class Router extends controllers.helpers.Router {
		@Override
		public play.mvc.Call page() {
			return controllers.routes.Users.crud.page(1);
		}

		@Override
		public play.mvc.Call show(Long id) {
			return controllers.routes.Users.crud.show(id);
		}

		public play.mvc.Call show(User user) {
			return show(user.id);
		}
	}
}