package controllers;

import models.Term;
import play.api.mvc.Call;
import views.html.terms.*;
import controllers.helpers.SecuredCrud;

public class Terms extends SecuredCrud<Term> {
	public Terms() {
		super(
				Term.crud,
				form(Term.class),
				update.ref(),
				create.ref(),
				page.ref(),
				null // show
		);
	}

	@Override
	protected Call callPage() {
		return controllers.routes.App.terms.page(1);
	}
}
