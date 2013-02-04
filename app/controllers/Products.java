package controllers;

import models.Product;
import controllers.helpers.SecuredCrud;
import play.mvc.Controller;
import views.html.products.*;


public class Products extends Controller {

	public final static SecuredCrud<Product> crud = new SecuredCrud<Product>(Product.crud,
			form(Product.class),
			update.ref(),
			create.ref(),
			page.ref(),
			null,
			routes.Products.crud.page(1));
//TODO
/*	@Transactional
	public static Result save() {
		static Form<forms.Product> FORM = form(forms.Product.class);
		Form<forms.Product> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Tuotteen tallennus peruutettu!");
			return redirect(routes.Products.index());
		} else if (!filledForm.hasErrors()) {
			Product product = new Product(filledForm.get());
			if (filledForm.field("new") != null
					&& filledForm.field("new").value() != null
					&& filledForm.field("new").value().equals("true")) {
				if (Product.crud.findById(product.id) != null) {
					product.id = null;
					flash("error", "Tuotenumero on jo käytössä!");
					return badRequest(views.html.products.manage.render(FORM
							.fill(new forms.Product(product))));
				}
			}
			// TODO smarter save/update
			if ((product.saveOrUpdate())) {
				flash("success", "Tuote on tallennettu onnistuneesti!");
				return redirect(routes.Products.index());
			}
		}
		flash("error", "Tuotteen tallennus ei onnistunut!");
		return badRequest(views.html.products.manage.render(filledForm));
	}*/
}
