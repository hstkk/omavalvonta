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

	@Transactional(readOnly = true)
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
		Form<models.Product> filledProductForm = productForm.bindFromRequest();
		if (filledProductForm.field("action").value().equals("peruuta")) {
			flash("status", "Tuotteen tallennus peruutettu!");
			return redirect(routes.Products.index());
		} else if (!filledProductForm.hasErrors()) {
			Product product = filledProductForm.get();
			if ((product.id != null && product.update())
					|| (product.id == null && product.save())) {
				flash("status", "Tuote on tallennettu onnistuneesti!");
				return redirect(routes.Products.index());
			}
		}
		flash("status", "Tuotteen tallennus ei onnistunut!");
		return badRequest(views.html.products.manage.render(filledProductForm));
	}
}
