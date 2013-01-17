package controllers;

import models.Ingredient;
import controllers.helpers.Crud;
import static play.data.Form.*;
import play.mvc.Controller;
import views.html.ingredients.*;

public class Ingredients extends Controller {

	public final static Crud<Ingredient> crud = new Crud<Ingredient>(Ingredient.crud,
			form(Ingredient.class),
			update.ref(),
			create.ref(),
			page.ref(),
			null,
			routes.Ingredients.crud.page(1, "", ""));
}
