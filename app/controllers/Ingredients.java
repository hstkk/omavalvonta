package controllers;

import models.Ingredient;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Ingredients.
 * 
 * @author Sami Hostikka <dev@01.fi>
 */
public class Ingredients extends Controller {

	/** The Constant FORM. */
	final static Form<Ingredient> FORM = form(Ingredient.class);

	/**
	 * Creates the.
	 * 
	 * @return the result
	 */
	public static Result create() {
		return views.html.ingredients.manage.render(FORM);
	}

	/**
	 * Index.
	 * 
	 * @return the result
	 */
	public static Result index() {
		return page(1);
	}

	/**
	 * Page.
	 * 
	 * @param index
	 *            the index
	 * @return the result
	 */
	@Transactional(readOnly = true)
	public static Result page(int index) {
		return views.html.ingredients.page.render(Ingredient.page(index));
	}

	/**
	 * Update.
	 * 
	 * @param ingredientId
	 *            the ingredient id
	 * @return the result
	 */
	@Transactional(readOnly = true)
	public static Result update(Long ingredientId) {
		Ingredient ingredient = Ingredient.findById(ingredientId);
		if (ingredient == null)
			return notFound(views.html.notFound.render());
		return views.html.ingredients.manage.render(FORM.fill(ingredient));
	}

	/**
	 * Save.
	 * 
	 * @return the result
	 */
	@Transactional
	public static Result save() {
		Form<Ingredient> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Raaka-aineen tallennus peruutettu!");
			return redirect(routes.FormTypes.index());
		} else if (!filledForm.hasErrors()) {
			Ingredient ingredient = filledForm.get();
			// TODO smarter save/update
			if ((ingredient.id != null && ingredient.update())
					|| (ingredient.id == null && ingredient.save())) {
				flash("success", "Tuote on tallennettu onnistuneesti!");
				return redirect(routes.FormTypes.index());
			}
		}
		flash("error", "Raaka-aineen tallennus ei onnistunut!");
		return badRequest(views.html.ingredients.manage.render(filledForm));
	}
}
