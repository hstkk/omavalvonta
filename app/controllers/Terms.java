package controllers;

import models.Term;
import controllers.helpers.SecuredCrud;
import play.mvc.Controller;
import views.html.terms.*;

public class Terms extends Controller{
	public final static SecuredCrud<Term> crud = new SecuredCrud<Term>(Term.crud,
			form(Term.class),
			update.ref(),
			create.ref(),
			page.ref(),
			null,
			routes.Terms.crud.page(1, "", ""));
}
