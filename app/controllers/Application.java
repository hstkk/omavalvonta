package controllers;

import play.*;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
  @Transactional
  public static Result index() {
	  forms.dynamicforms.Field field = new forms.dynamicforms.Field();
	  field.help = "help";
	  field.name = "test";
	  field.type = models.dynamicforms.FieldType.TEXT;
	  field.isRequired = true;
	  field.isSigned = false;
	  new models.dynamicforms.Field(field);
    return ok(index.render("Your new application is ready."));
  }
  
}