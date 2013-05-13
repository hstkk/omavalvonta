package controllers;

import static play.data.Form.form;
import models.Batch;
import play.db.jpa.Transactional;
import play.mvc.Call;
import play.mvc.Result;
import play.mvc.With;
import play.mvc.Security.Authenticated;
import utils.Converter;
import views.html.batches.*;
import controllers.helpers.UserCrud;
import controllers.shib.Secured;
import controllers.shib.Session;

@With(Session.class)
public class Batches extends UserCrud<Batch> {

	public Batches() {
		super(Batch.dao, null, null, page.ref(), show.ref(), null);
	}

	@Override
	public Call callPage() {
		return controllers.routes.Batches.page(1);
	}

	@Override
	public Call callShow(Long id) {
		return controllers.routes.Batches.show(id);
	}

	@Override
	@Authenticated(Secured.class)
	public Result ack(Long id) {
		return super.ack(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Result fresh() {
		String value = request().getQueryString("tuote");
		if (value != null && !value.isEmpty()) {
			Long product = Converter.stringToLong(value);
			if (product != null)
				return redirect(controllers.routes.BatchStep2.fresh(product));
		}
		return ok(step1.render(form(Batch.class)));
	}

	@Override
	@Transactional(readOnly = true)
	public Result page(int pageNumber) {
		return super.page(pageNumber);
	}

	@Override
	@Transactional(readOnly = true)
	public Result show(Long id) {
		return super.show(id);
	}
}