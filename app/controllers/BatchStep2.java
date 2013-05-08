package controllers;

import models.Batch;
import models.Product;
import models.User;
import play.data.Form;
import static play.data.Form.*;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.mvc.Call;
import play.mvc.Result;
import play.mvc.With;
import utils.Helper;
import views.html.batches.*;
import controllers.helpers.UserCrud;
import controllers.shib.Session;

@With(Session.class)
public class BatchStep2 extends UserCrud<Batch> {
	public BatchStep2() {
		super(null, form(Batch.class), null, null, null, null);
	}

	@Override
	public Call callShow(Long id) {
		return controllers.routes.Batches.show(id);
	}

	@Transactional
	public Result create(Long productId) {
		Product product = Product.dao.getReference(productId);
		if (product == null)
			return Helper.getNotFound();
		Form<Batch> filledForm = form(Batch.class, Batch.Step2.class)
				.bindFromRequest();
		Result result = onCancel(filledForm);
		if (result != null)
			return result;
		if (!filledForm.hasErrors()) {
			Batch batch = filledForm.get();
			User user = Session.user();
			boolean success = batch.bind(user, product);
			if (success) {
				success = Batch.dao.create(batch);
				if (success) {
					flash("success", Messages.get("crud.success"));
					return redirect(callShow(batch.id));
				}
			}
		}
		Batch batch = new Batch(product);
		flash("error", Messages.get("crud.fail"));
		return badRequest(step2.render(batch, filledForm));
	}

	@Transactional(readOnly = true)
	public Result fresh(Long productId) {
		Product product = Product.dao.findById(productId);
		if (product == null)
			return Helper.getNotFound();
		Batch batch = new Batch(product);
		Form<Batch> preFilledForm = batch.getPrefilledForm();
		return ok(step2.render(batch, preFilledForm));
	}
}