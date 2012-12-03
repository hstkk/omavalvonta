package controllers.helpers;

import models.dynamicforms.Fieldset;
import play.*;
import play.mvc.*;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.data.*;
import static play.data.Form.*;
import play.db.jpa.*;

public class Crud<T> extends Controller implements CrudInterface {

	private final Class<T> clazz;
	private final models.helpers.Crud<T, Long> CRUD;
	private final Form<T> FORM;
	private final Template1<models.helpers.Page<T>, Html> PAGETEMPLATE;

	public Crud(Class<T> clazz, models.helpers.Crud<T, Long> CRUD,
			Form<T> FORM,
			Template1<models.helpers.Page<T>, Html> PAGETEMPLATE) {
		this.clazz = clazz;
		this.CRUD = CRUD;
		this.FORM = FORM;
		this.PAGETEMPLATE = PAGETEMPLATE;
	}

	@Override
	public Result page(int index) {
		// TODO Auto-generated method stub
		return null;
	}
}
