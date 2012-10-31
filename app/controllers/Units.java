package controllers;

import controllers.helpers.Crud;
import models.Term;
import models.TermCategory;
import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class Units extends Controller {

	private final static models.helpers.Crud<Term, Long> CRUD = Term.crud;
	private final static Form<forms.Term> FORM = form(forms.Term.class);
	private final static TermCategory CATEGORY = TermCategory.UNIT;
	
	public final static Crud<Term, forms.Term, Long> crud = new Crud<Term, forms.Term, Long>(CRUD, FORM, CATEGORY);
}
