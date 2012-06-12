package controllers;

import models.Product;
import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class Products extends Controller {

	final static Form<models.Product> productForm = form(models.Product.class);

	@Transactional(readOnly = true)
	public static Result index() {
		return page(1);
	}

	@Transactional(readOnly = true)
	public static Result page(int page) {
		return ok(views.html.products.all.render(models.Product.findAll()));
	}

	public static Result add() {
		return ok(views.html.products.manage.render(productForm));
	}

	@Transactional(readOnly = true)
	public static Result edit(Long productId) {
		Product product = Product.findById(productId);
		if (product == null)
			return notFound(views.html.notFound.render());
		return ok(views.html.products.manage.render(productForm.fill(product)));
	}

	@Transactional
	public static Result save() {
		return TODO;
	}
}
