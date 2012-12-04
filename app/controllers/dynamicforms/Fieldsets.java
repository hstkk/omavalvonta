package controllers.dynamicforms;

import play.api.templates.*;
import play.data.*;
import play.mvc.Controller;
import static play.data.Form.*;

import models.dynamicforms.Fieldset;

import controllers.helpers.Crud;

public class Fieldsets extends Controller {
	final static Template1<models.helpers.Page<Fieldset>, Html> PAGETEMPLATE = views.html.dynamicforms.fieldsets.page
			.ref();
	final static Template1<Form<Fieldset>, Html> CREATETEMPLATE = views.html.dynamicforms.fieldsets.create
			.ref();
	final static Template2<Long, Form<Fieldset>, Html> UPDATETEMPLATE = views.html.dynamicforms.fieldsets.update
			.ref();

	public final static Crud<Fieldset> crud = new Crud<Fieldset>(
			Fieldset.class, PAGETEMPLATE, CREATETEMPLATE, UPDATETEMPLATE);
}
