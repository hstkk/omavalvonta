package controllers;

import models.Term;
import play.mvc.Call;
import play.mvc.Controller;
import views.html.terms.*;
import controllers.helpers.SecuredCrud;

public class Terms extends Controller {
	public final static SecuredCrud<Term> crud = new SecuredCrud<Term>(
			Term.crud,
			form(Term.class),
			new Router(),
			create.ref(),
			page.ref(),
			null,
			update.ref()
		);

	public static class Router extends controllers.helpers.Router {
		@Override
		public Call page() {
			return controllers.routes.Terms.crud.page(1);
		}
	}
}
