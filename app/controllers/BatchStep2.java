package controllers;

import models.Batch;
import models.Product;
import models.Batch.Step2;
import models.Ingredient;
import models.User;
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
import controllers.shib.Session;

@With(Session.class)
public class BatchStep2 extends UserCrud<Batch> {
	public BatchStep2() {
		super(null, form(Batch.class), null, null, null, null);
	}

	private final Template2<Long, Form<Batch>, Html> CREATE = views.html.batches.step2
			.ref();

	@Override
	public Call callShow(Long id) {
		return controllers.routes.Batches.show(id);
	}

	@Transactional
	public Result create(Long productId) {
		Product product = Product.dao.findById(productId);
		if (product == null)
			return notFound();
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
		flash("error", Messages.get("crud.fail"));
		return badRequest(CREATE.render(product.id, filledForm));
	}

	@Transactional(readOnly = true)
	public Result fresh(Long productId) {
		Product product = Product.dao.findById(productId);
		if (product == null)
			return notFound();
		return ok(CREATE.render(product.id, FORM));
	}
}