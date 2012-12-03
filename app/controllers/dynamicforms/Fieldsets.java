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

public class Fieldsets extends Controller {
	final static models.helpers.Crud<Fieldset, Long> CRUD = new models.helpers.Crud<Fieldset, Long>(
			Fieldset.class, "Fieldset");
	final static Form<models.dynamicforms.Fieldset> FORM = form(models.dynamicforms.Fieldset.class);
	/*
	 * final static Map<String, String> TEMPLATES; static { Map<String, String>
	 * map = new HashMap<String, String>(); map.put("Harry", "Potter");
	 * TEMPLATES = Collections.unmodifiableMap(map); }
	 */
	final static Template1<models.helpers.Page<Fieldset>, Html> PAGETEMPLATE = views.html.dynamicforms.fieldsets.page
			.ref();
	final static Template1<Form<Fieldset>, Html> CREATETEMPLATE = views.html.dynamicforms.fieldsets.create
			.ref();
	final static Template2<Long, Form<Fieldset>, Html> UPDATETEMPLATE = views.html.dynamicforms.fieldsets.update
			.ref();;

	public final static Crud<Fieldset> crud = new Crud<Fieldset>(
			Fieldset.class, CRUD, FORM, PAGETEMPLATE, CREATETEMPLATE,
			UPDATETEMPLATE);
}
