package controllers.dynamicforms;

import models.dynamicforms.Fieldset;
import controllers.helpers.Crud;
import play.mvc.Controller;
import views.html.dynamicforms.fieldsets.*;

public class Fieldsets extends Controller {
	public final static Crud<Fieldset> crud = new Crud<Fieldset>(Fieldset.crud,
			form(Fieldset.class),
			update.ref(),
			create.ref(),
			page.ref(),
			null,
			routes.Fieldsets.crud.page(1, "", ""));
}
