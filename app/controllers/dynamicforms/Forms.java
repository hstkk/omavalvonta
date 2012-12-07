package controllers.dynamicforms;

import models.dynamicforms.Form;
import controllers.helpers.Crud;
import play.mvc.Controller;
import views.html.dynamicforms.forms.*;

public class Forms extends Controller {
	public final static Crud<Form> crud = new Crud<Form>(Form.crud,
			form(Form.class),
			update.ref(), create.ref(), page.ref(), null,
			routes.Forms.crud.page(0));
}
