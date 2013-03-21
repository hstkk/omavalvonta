package controllers;

import models.helpers.Page;
import play.db.jpa.Transactional;
import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.news.*;
import controllers.helpers.SecuredCrud;

public class News extends Controller {
	public final static SecuredCrud<models.News> crud = new SecuredCrud<models.News>(
			models.News.crud,
			form(models.News.class),
			new Router(),
			create.ref(),
			page.ref(),
			null,
			update.ref()
		);

	public static class Router extends controllers.helpers.Router {
		@Override
		public Call page() {
			return controllers.routes.News.crud.page(1);
		}
	}

	@Transactional(readOnly = true)
	public static Result index(int pageNumber) {
		Page<models.News> page = models.News.crud.page(pageNumber);
		return ok(index.render(page));
	}
}
