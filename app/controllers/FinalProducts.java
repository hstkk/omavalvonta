package controllers;

import models.Batch;
import models.FinalProduct;
import models.User;
import play.api.templates.Html;
import play.api.templates.Template2;
import play.data.Form;
import static play.data.Form.*;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.mvc.Call;
import play.mvc.Result;
import play.mvc.With;
import play.mvc.Security.Authenticated;
import utils.Helper;
import views.html.finalproducts.*;
import controllers.helpers.UserCrud;
import controllers.shib.Secured;
import controllers.shib.Session;

@With(Session.class)
public class FinalProducts extends UserCrud<FinalProduct> {

	public FinalProducts() {
		super(FinalProduct.dao, form(FinalProduct.class), null, null, null,
				null);
	}

	private final Template2<Form<FinalProduct>, Batch, Html> FIELDS = fields
			.ref();

	@Override
	@Authenticated(Secured.class)
	public Result ack(Long id) {
		// TODO Auto-generated method stub
		return super.ack(id);
	}

	@Override
	public Call callShow(Long id) {
		return controllers.routes.Batches.show(id);
	}

	@Transactional
	public Result save(Long batchId) {
		Batch batch = Batch.dao.getReference(batchId);
		if (batch == null)
			return Helper.getNotFound();
		Form<FinalProduct> filledForm = form(FinalProduct.class,
				FinalProduct.Partial.class).bindFromRequest();
		Result result = onCancel(filledForm);
		if (result != null)
			return result;
		if (!filledForm.hasErrors()) {
			FinalProduct finalProduct = filledForm.get();
			finalProduct.batch = batch;
			User user = Session.user();
			finalProduct.user = user;

			boolean success = false;
			FinalProduct finalProductOriginal = FinalProduct.findByBatch(batch);
			if (finalProductOriginal == null)
				success = DAO.create(finalProduct);
			else {
				finalProduct.id = finalProductOriginal.id;
				success = DAO.update(finalProduct);
			}

			if (success) {
				flash("success", Messages.get("crud.success"));
				return redirect(callShow(batch.id));
			}
		}
		flash("error", Messages.get("crud.fail"));
		return badRequest(FIELDS.render(filledForm, batch));
	}

	@Transactional(readOnly = true)
	public Result edit(Long batchId) {
		Batch batch = Batch.dao.getReference(batchId);
		if (batch == null)
			return Helper.getNotFound();
		FinalProduct finalProduct = FinalProduct.findByBatch(batch);
		if (finalProduct != null)
			return ok(FIELDS.render(FORM.fill(finalProduct), batch));
		return ok(FIELDS.render(FORM, batch));
	}
}