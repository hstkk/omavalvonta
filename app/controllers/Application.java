package controllers;

import play.*;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
  @Transactional
  public static Result index() {
	  models.Field.findById(1);
    return ok(index.render("Your new application is ready."));
  }
  
}