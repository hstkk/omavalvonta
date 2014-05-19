package controllers;

import static play.data.Form.form;
import models.Batch;
import models.FinalProduct;
import models.User;
import models.helpers.Dao;
import models.helpers.Page;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.libs.F;
import play.mvc.Call;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import play.mvc.With;
import utils.Helper;
import views.html.finalproducts.fields;
import controllers.helpers.UserCrud;
import controllers.shib.Secured;
import controllers.shib.Session;

@With(Session.class)
public class FinalProducts extends UserCrud<FinalProduct> {

	public FinalProducts() {
		super(
			F.Option.<Dao<FinalProduct, Long>>Some(FinalProduct.dao),
			F.Option.<Form<FinalProduct>>Some(form(FinalProduct.class)),
			F.Option.<Template1<Form<FinalProduct>, Html>>None(),
			F.Option.<Template1<Page<FinalProduct>, Html>>None(),
			F.Option.<Template1<FinalProduct, Html>>None(),
			F.Option.<Template2<FinalProduct, Form<FinalProduct>, Html>>None()
		);
	}

	private final Template2<Form<FinalProduct>, Batch, Html> FIELDS = fields
			.ref();

	@Override
	@Authenticated(Secured.class)
	@Transactional
	public Result ack(Long batchId) {
		User user = Session.user();
		if (user != null) {
			Batch batch = Batch.dao.getReference(batchId);
			if (batch == null)
				return Helper.getNotFound();
			FinalProduct finalProduct = FinalProduct.findByBatch(batch);
			if (finalProduct == null)
				return Helper.getNotFound();
			if (finalProduct.user != null)
				return Helper.getInternalServerError();
			finalProduct.user = user;
			if (DAO.update(finalProduct)) {
				flash().remove("error");
				flash("success", Messages.get("crud.success"));
			} else
				flash("error", Messages.get("crud.fail"));
			return redirect(callShow(batchId));
		}
		return Helper.getUnauthorized();
	}

	@Override
	public Call callShow(Long id) {
		return controllers.routes.Batches.show(id);
	}

	@Transactional
	public Result save(Long batchId) {
		Batch batch = Batch.dao.findById(batchId);
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
				flash().remove("error");
				flash("success", Messages.get("crud.success"));
				return redirect(callShow(batch.id));
			}
		}
		flash("error", Messages.get("crud.fail"));
		return badRequest(FIELDS.render(filledForm, batch));
	}

	@Transactional(readOnly = true)
	public Result edit(Long batchId) {
		Batch batch = Batch.dao.findById(batchId);
		if (batch == null)
			return Helper.getNotFound();
		FinalProduct finalProduct = FinalProduct.findByBatch(batch);
		if (finalProduct != null)
			return ok(FIELDS.render(FORM.fill(finalProduct), batch));
		return ok(FIELDS.render(FORM, batch));
	}
}