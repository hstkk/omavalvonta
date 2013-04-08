package controllers;

import models.Batch;
import models.Batch.Step2;
import models.Ingredient;
import models.helpers.Dao;
import models.helpers.Page;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import static play.data.Form.*;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import play.mvc.Security.Authenticated;
import views.html.batches.*;
import controllers.helpers.Crud;
import controllers.helpers.UserCrud;
import controllers.shib.Secured;
import controllers.shib.SessionTimeout;

public class BatchStep1 extends Crud<Batch> {
	public BatchStep1() {
		super(null, form(Batch.class), views.html.batches.step1.ref(), null,
				null, null);
	}

	@Override
	protected Form<Batch> bindForm() {
		return form(Batch.class, Batch.Step1.class).bindFromRequest();
	}

	@Override
	@Transactional(readOnly = true)
	public Result create() {
		return super.create();
	}

	@Override
	@Transactional(readOnly = true)
	public Result fresh() {
		return super.fresh();
	}

	@Override
	public Result onCreateOrUpdate(Batch t, Long id) {
		return temporaryRedirect(controllers.routes.BatchStep2
				.fresh(t.product.id));
	}
}