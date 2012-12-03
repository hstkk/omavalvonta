package controllers.dynamicforms;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import play.api.templates.Html;
import play.api.templates.Template1;
import play.data.*;
import static play.data.Form.*;

import play.*;
import play.mvc.*;
//import play.data.*;
//import static play.data.Form.*;
import play.db.jpa.*;

import models.dynamicforms.Field;
import models.dynamicforms.Fieldset;

public class Fieldsets extends Controller {
	final static models.helpers.Crud<Fieldset, Long> CRUD = new models.helpers.Crud<Fieldset, Long>(
			Fieldset.class);
	final static Form<Field> FORM = form(Field.class);
	//final static Form<models.dynamicforms.Fieldset> FORM = play.data.Form.form(models.dynamicforms.Fieldset.class);
	final static Map<String, String> TEMPLATES;
	static {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Harry", "Potter");
		TEMPLATES = Collections.unmodifiableMap(map);
	}
	//final static Template1 <models.helpers.Page<Fieldset>, Html> TEMPLATE =
	//final static Html HTML = views.html.dynamicforms.fieldsets.page.ref;
	//final static Template1 <models.helpers.Page<Fieldset>, Html> TEMPLATE =
	Template1<models.helpers.Page<Fieldset>, Html> TEMPLATE = views.html.dynamicforms.fieldsets.page.ref();
}
