package controllers;

import static play.data.Form.form;
import models.Term;
import models.helpers.Dao;
import models.helpers.Page;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.F;
import play.mvc.Call;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import play.mvc.With;
import views.html.terms.create;
import views.html.terms.page;
import views.html.terms.update;
import controllers.helpers.SecuredCrud;
import controllers.shib.Secured;
import controllers.shib.Session;

@With(Session.class)
@Authenticated(Secured.class)
public class Terms extends SecuredCrud<Term> {
	public Terms() {
		super(
			F.Option.<Dao<Term, Long>>Some(Term.dao),
			F.Option.<Form<Term>>Some(form(Term.class)),
			F.Option.<Template1<Form<Term>, Html>>Some(create.ref()),
			F.Option.<Template1<Page<Term>, Html>>Some(page.ref()),
			F.Option.<Template1<Term, Html>>None(),
			F.Option.<Template2<Term, Form<Term>, Html>>Some(update.ref())
		);
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
