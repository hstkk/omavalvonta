package controllers;

import static play.data.Form.form;
import models.Batch;
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
import utils.Converter;
import views.html.batches.page;
import views.html.batches.show;
import views.html.batches.step1;
import controllers.helpers.UserCrud;
import controllers.shib.Secured;
import controllers.shib.Session;

@With(Session.class)
public class Batches extends UserCrud<Batch> {

	public Batches() {
		super(
			F.Option.<Dao<Batch, Long>>Some(Batch.dao),
			F.Option.<Form<Batch>>None(),
			F.Option.<Template1<Form<Batch>, Html>>None(),
			F.Option.<Template1<Page<Batch>, Html>>Some(page.ref()),
			F.Option.<Template1<Batch, Html>>Some(show.ref()),
			F.Option.<Template2<Batch, Form<Batch>, Html>>None()
		);
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
	@Transactional
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