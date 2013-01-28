package controllers.dynamicforms;

import models.dynamicforms.Form;
import controllers.helpers.SecuredCrud;
import play.mvc.Controller;
import views.html.dynamicforms.forms.*;

public class Forms extends Controller {
	public final static SecuredCrud<Form> crud = new SecuredCrud<Form>(Form.crud,
			form(Form.class),
			update.ref(),
			create.ref(),
			page.ref(),
			null,
			routes.Forms.crud.page(1, "", ""));
}
