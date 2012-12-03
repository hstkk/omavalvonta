package controllers.dynamicforms;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import play.api.templates.*;
import play.data.*;
import static play.data.Form.*;

import play.*;
import play.mvc.*;
//import play.data.*;
//import static play.data.Form.*;
import play.db.jpa.*;

import models.dynamicforms.Form;

import controllers.helpers.Crud;

public class Forms extends Controller {
	final static models.helpers.Crud<Form, Long> CRUD = new models.helpers.Crud<Form, Long>(
			Form.class, "Form");
	final static play.data.Form<Form> FORM = form(models.dynamicforms.Form.class);
	final static Template1<models.helpers.Page<Form>, Html> PAGETEMPLATE = views.html.dynamicforms.forms.page
			.ref();
	final static Template1<play.data.Form<Form>, Html> CREATETEMPLATE = views.html.dynamicforms.forms.create
			.ref();
	final static Template2<Long, play.data.Form<Form>, Html> UPDATETEMPLATE = views.html.dynamicforms.forms.update
			.ref();;

	public final static Crud<Form> crud = new Crud<Form>(Form.class, CRUD,
			FORM, PAGETEMPLATE, CREATETEMPLATE, UPDATETEMPLATE);
}
