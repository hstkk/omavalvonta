package controllers.dynamicforms;

import play.api.templates.*;
import play.data.*;
import play.mvc.Controller;

import models.dynamicforms.Form;

import controllers.helpers.Crud;

public class Forms extends Controller {
	final static Template1<models.helpers.Page<Form>, Html> PAGETEMPLATE = views.html.dynamicforms.forms.page
			.ref();
	final static Template1<play.data.Form<Form>, Html> CREATETEMPLATE = views.html.dynamicforms.forms.create
			.ref();
	final static Template2<Long, play.data.Form<Form>, Html> UPDATETEMPLATE = views.html.dynamicforms.forms.update
			.ref();

	public final static Crud<Form> crud = new Crud<Form>(Form.class,
			PAGETEMPLATE, CREATETEMPLATE, UPDATETEMPLATE);
}
