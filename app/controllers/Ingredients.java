package controllers;

import models.Ingredient;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

public class Ingredients extends Controller {
	final static Form<Ingredient> FORM = form(Ingredient.class);

	@Transactional(readOnly = true)
	public static Result create() {
		return TODO;
	}

	@Transactional(readOnly = true)
	public static Result index() {
		return page(1);
	}

	@Transactional(readOnly = true)
	public static Result page(int index) {
		return views.html.ingredients.page.render(Ingredient.page(index));
	}

	@Transactional(readOnly = true)
	public static Result read() {
		return TODO;
	}

	@Transactional
	public static Result save() {
		return TODO;
	}
}
