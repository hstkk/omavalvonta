package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.db.jpa.*;

import models.Ingredient;
import models.IngredientSupply;
import models.Product;
import models.helpers.KeyValue;

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
		return ok(views.html.products.manage.render(FORM
				.fill(new forms.Product(product))));
	}

	@Transactional
	public static Result save() {
		Form<forms.Product> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Tuotteen tallennus peruutettu!");
			return redirect(routes.Products.index());
		} else if (!filledForm.hasErrors()) {
			Product product = new Product(filledForm.get());
			if (filledForm.field("new") != null
					&& filledForm.field("new").value() != null
					&& filledForm.field("new").value().equals("true")) {
				if (Product.findById(product.id) != null) {
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
	}

	@Transactional(readOnly = true)
	public static Result batch(Long productId) {
		Product product = Product.findById(productId);
		if (product == null)
			return notFound(views.html.notFound.render());
		if (product.ingredients == null || product.ingredients.isEmpty())
			return ok("Tuotteen raaka-aineita ei löytynyt");
		StringBuilder stringBuilder = new StringBuilder();
		int index = 0;
		for (Ingredient ingredient : product.ingredients) {
			KeyValue<String, Integer> keyvalue = IngredientSupply
					.findAliveByIngredient(ingredient, index);
			index = keyvalue.value;
			stringBuilder.append(keyvalue.key);
		}
		return ok(stringBuilder.toString());
	}

}
