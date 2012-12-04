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

import models.dynamicforms.Field;
import models.dynamicforms.Fieldset;

import controllers.helpers.Crud;
import controllers.helpers.Render;

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
