package controllers;

import models.Batch;
import play.data.Form;
import static play.data.Form.*;
import play.db.jpa.Transactional;
import play.mvc.Result;
import play.mvc.With;
import controllers.helpers.Crud;
import controllers.shib.Session;

@With(Session.class)
public class BatchStep1 extends Crud<Batch> {
	public BatchStep1() {
		super(null, form(Batch.class), step1.ref(), null,
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