package controllers;

import play.*;
import play.db.jpa.Transactional;
import play.mvc.*;
import java.util.*;

import views.html.*;

public class Application extends Controller {

	@Transactional
	public static Result index() {
		/*forms.dynamicforms.Field field = new forms.dynamicforms.Field();
		field.help = "help";
		field.name = "test";
		field.type = models.dynamicforms.FieldType.TEXT;
		field.isRequired = true;
		field.isSigned = false;
		//models.dynamicforms.Field f = new models.dynamicforms.Field(field);
		forms.dynamicforms.Manage man = new forms.dynamicforms.Manage();
		man.description = "desc";
		man.isActive = false;
		man.name = "test";
		models.dynamicforms.Form form = new models.dynamicforms.Form(man);
		form.addField(field);*/
		//models.dynamicforms.Form form = models.dynamicforms.Form.findById(Long.parseLong("1"));
		/*form.add(f);
		form.save();*/
		//models.dynamicforms.Field.findById(Long.parseLong("1")).delete();
		
		//models.dynamicforms.Form asd = models.dynamicforms.Form.findById(Long.parseLong("1"));
		/*List<models.dynamicforms.Field> asd = models.dynamicforms.Field.findByForm(models.dynamicforms.Form.findById(Long.parseLong("1")));
		if(asd != null)
			System.out.println("\n\n"+asd.get(0).name+"\n\n");*/
		
		return ok(index.render("Your new application is ready."));
	}

}