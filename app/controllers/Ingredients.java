package controllers;

import models.Ingredient;
import models.Product;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

public class Ingredients extends Controller {
	final static Form<Ingredient> FORM = form(Ingredient.class);

	public static Result create() {
		return views.html.ingredients.manage.render(FORM);
	}

	public static Result index() {
		return page(1);
	}

	@Transactional(readOnly = true)
	public static Result page(int index) {
		return views.html.ingredients.page.render(Ingredient.page(index));
	}

	@Transactional(readOnly = true)
	public static Result update(Long ingredientId) {
		Ingredient ingredient = Ingredient.findById(ingredientId);
		if (ingredient == null)
			return notFound(views.html.notFound.render());
		return views.html.ingredients.manage.render(FORM.fill(ingredient));
	}

	@Transactional
	public static Result save() {
		Form<Ingredient> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Raaka-aineen tallennus peruutettu!");
			return redirect(routes.Ingredients.index());
		} else if (!filledForm.hasErrors()) {
			Ingredient ingredient = filledForm.get();
			// TODO smarter save/update
			if ((ingredient.id != null && ingredient.update())
					|| (ingredient.id == null && ingredient.save())) {
				flash("success", "Tuote on tallennettu onnistuneesti!");
				return redirect(routes.Ingredients.index());
			}
		}
		flash("error", "Raaka-aineen tallennus ei onnistunut!");
		return badRequest(views.html.ingredients.manage.render(filledForm));
	}
}
