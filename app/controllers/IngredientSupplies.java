package controllers;

import models.IngredientSupply;
import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class IngredientSupplies extends Controller {

	final static Form<IngredientSupply> FORM = form(IngredientSupply.class);

	public static Result create() {
		return views.html.ingredientsupplies.manage.render(FORM);
	}

	public static Result index() {
		return page(1);
	}

	@Transactional(readOnly = true)
	public static Result page(int index) {
		return views.html.ingredientsupplies.page.render(Product.page(index));
	}

	@Transactional
	public static Result save() {
		Form<Product> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Tuotteen tallennus peruutettu!");
			return redirect(routes.IngredientSupplies.index());
		} else if (!filledForm.hasErrors()) {
			Product product = filledForm.get();
			// TODO smarter save/update
			if (product.save()) {
				flash("success", "Tuote on tallennettu onnistuneesti!");
				return redirect(routes.IngredientSupplies.index());
			}
		}
		flash("error", "Tuotteen tallennus ei onnistunut!");
		return badRequest(views.html.ingredientsupplies.manage.render(filledForm));
	}
}
