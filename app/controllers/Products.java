package controllers;

import models.Product;
import play.mvc.Call;
import play.mvc.Controller;
import views.html.products.*;
import controllers.helpers.SecuredCrud;

public class Products extends Controller {
	public final static SecuredCrud<Product> crud = new SecuredCrud<Product>(
			Product.crud,
			form(Product.class),
			new Router(),
			create.ref(),
			page.ref(),
			null,
			update.ref()
		);

	public static class Router extends controllers.helpers.Router {
		@Override
		public Call page() {
			return controllers.routes.Products.crud.page(1);
		}
	}
}
