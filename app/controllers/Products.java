package controllers;

import models.Product;
import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class Products extends Controller {

	final static Form<forms.Product> FORM = form(forms.Product.class);

	@Transactional(readOnly = true)
	public static Result create() {
		return ok(views.html.products.manage.render(FORM));
	}

	@Transactional(readOnly = true)
	public static Result index() {
		return page(1);
	}

	@Transactional(readOnly = true)
	public static Result page(int index) {
		return ok(views.html.products.page.render(Product.page(index)));
	}

	@Transactional(readOnly = true)
	public static Result update(Long productId) {
		Product product = Product.findById(productId);
		if (product == null)
			return notFound(views.html.notFound.render());
		return ok(views.html.products.manage.render(FORM.fill(new forms.Product(product))));
	}

	@Transactional
	public static Result save() {
		Form<forms.Product> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Tuotteen tallennus peruutettu!");
			return redirect(routes.Products.index());
		} else if (!filledForm.hasErrors()) {
			Product product = new Product(filledForm.get());
			// TODO smarter save/update
			if ((product.id != null && product.update())
					|| (product.id == null && product.save())) {
				flash("success", "Tuote on tallennettu onnistuneesti!");
				return redirect(routes.Products.index());
			}
		}
		flash("error", "Tuotteen tallennus ei onnistunut!");
		return badRequest(views.html.products.manage.render(filledForm));
	}

	@Transactional(readOnly = true)
	public static Result batch(Long productId) {
		Product product = Product.findById(productId);
		if (product == null)
			return notFound(views.html.notFound.render());
		return ok("HELLO");
	}

}
