package controllers;

import models.Term;
import models.helpers.Dao;
import models.helpers.Page;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import static play.data.Form.*;
import play.db.jpa.Transactional;
import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.terms.*;
import controllers.helpers.SecuredCrud;
import controllers.shib.Session;

@With(Session.class)
public class Terms extends SecuredCrud<Term> {
	public Terms() {
		super(Term.dao, form(Term.class), create.ref(), page.ref(), null,
				update.ref());
	}

	@Override
	public Call callPage() {
		return controllers.routes.Terms.page(1);
	}

	@Override
	@Transactional
	public Result create() {
		return super.create();
	}

	@Override
	@Transactional(readOnly = true)
	public Result page(int pageNumber) {
		return super.page(pageNumber);
	}

	@Override
	@Transactional
	public Result update(Long id) {
		return super.update(id);
	}
}
