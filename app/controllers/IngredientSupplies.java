package controllers;

import java.util.List;

import models.Batch;
import models.IngredientSupply;
import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class IngredientSupplies extends Controller {

	final static Form<IngredientSupply> FORM = form(IngredientSupply.class);

	public static Result create() {
		return ok(views.html.ingredientsupplies.manage.render(FORM));
	}

	public static Result index() {
		return page(1);
	}

	@Transactional(readOnly = true)
	public static Result page(int index) {
		return ok(views.html.ingredientsupplies.page.render(IngredientSupply
				.page(index)));
	}

	@Transactional
	public static Result save() {
		Form<IngredientSupply> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Raaka-aineen vastaanoton tallennus peruutettu!");
			return redirect(routes.IngredientSupplies.index());
		} else if (!filledForm.hasErrors()) {
			IngredientSupply ingredientSupply = filledForm.get();
			// TODO smarter save/update
			if (ingredientSupply.save()) {
				flash("success", "Raaka-aineen vastaanotto on tallennettu onnistuneesti!");
				return redirect(routes.IngredientSupplies.index());
			}
		}
		flash("error", "Raaka-aineen vastaanoton tallennus ei onnistunut!");
		return badRequest(views.html.ingredientsupplies.manage
				.render(filledForm));
	}

	@Transactional(readOnly = true)
	public static Result read(Long ingredientId) {
		IngredientSupply ingredientSupply = IngredientSupply
				.findById(ingredientId);
		if (ingredientSupply == null)
			return notFound(views.html.notFound.render());
		List<Batch> batches = null;
		return ok(views.html.ingredientsupplies.read.render(ingredientSupply,
				batches));
	}
}
