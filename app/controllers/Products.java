package controllers;

import models.Product;
import play.api.mvc.Call;
import views.html.products.*;
import controllers.helpers.SecuredCrud;

public class Products extends SecuredCrud<Product> {
	public Products() {
		super(
				Product.crud,
				form(Product.class),
				update.ref(),
				create.ref(),
				page.ref(),
				null // show
		);
	}

	@Override
	protected Call callPage() {
		return controllers.routes.App.products.page(1);
	}
}
