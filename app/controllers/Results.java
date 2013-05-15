package controllers;

import models.FinalProduct;
import models.Product;
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
import play.mvc.Result;
import play.mvc.With;
import play.mvc.Security.Authenticated;
import utils.Helper;
import views.html.results.*;
import controllers.helpers.Crud;
import controllers.shib.Secured;
import controllers.shib.Session;
import util.pdf.PDF;

@With(Session.class)
public class Results extends Crud<models.dynamicforms.Results> {
	public Results() {
		super(models.dynamicforms.Results.dao,
				form(models.dynamicforms.Results.class), step1.ref(), page
						.ref(), show.ref(), update.ref());
	}

	@Authenticated(Secured.class)
	public Result ack(Long resultsId, Long resultId) {
		User user = Session.user();
		if (user != null) {
			models.dynamicforms.Result t = models.dynamicforms.Result.dao
					.findById(resultId);
			if (t == null)
				return Helper.getNotFound();
			if (t.user != null)
				return Helper.getInternalServerError();
			t.user = user;
			if (models.dynamicforms.Result.dao.update(t))
				flash("success", Messages.get("crud.success"));
			else
				flash("error", Messages.get("crud.fail"));
			return redirect(callShow(resultsId));
		}
		return Helper.getUnauthorized();
	}

	@Override
	protected Form<models.dynamicforms.Results> bindForm() {
		Form<models.dynamicforms.Results> filledForm = form(
				models.dynamicforms.Results.class,
				models.dynamicforms.Results.Update.class).bindFromRequest();
		return filledForm;
	}

	@Override
	public Call callPage() {
		return controllers.routes.Results.page(1);
	}

	@Override
	public Call callShow(Long id) {
		return controllers.routes.Results.show(id);
	}

	@Override
	@Transactional
	public Result create() {
		if (DAO == null)
			return Helper.getInternalServerError();
		Form<models.dynamicforms.Results> filledForm = form(
				models.dynamicforms.Results.class,
				models.dynamicforms.Results.Step3.class).bindFromRequest();
		Result result = onCancel(filledForm);
		if (result != null)
			return result;
		models.dynamicforms.Results t = filledForm.get();
		Product product = Product.dao.getReference(t.product);
		models.dynamicforms.Form form = models.dynamicforms.Form.dao
				.getReference(t.form);
		if (product == null || form == null)
			return Helper.getNotFound();
		if (!filledForm.hasErrors()) {
			boolean success = DAO.create(t);
			if (success) {
				Call call = callShow(t.id);
				return redirect(call);
			}
		}
		flash("error", Messages.get("crud.fail"));
		return badRequest(step3.render(filledForm, product, form));
	}

	@Override
	@Transactional(readOnly = true)
	public Result edit(Long id) {
		return super.edit(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Result fresh() {
		return super.fresh();
	}

	@Transactional(readOnly = true)
	public Result history(Long id) {
		if (DAO == null)
			return Helper.getInternalServerError();
		models.dynamicforms.Results t = DAO.findById(id);
		if (t == null)
			return Helper.getNotFound();
		return ok(history.render(t));
	}

	@Override
	public Result isUpdatable(models.dynamicforms.Results t) {
		if (t == null)
			return Helper.getNotFound();
		else if (FinalProduct.isFinished(t.batches)) {
			flash("warning", Messages.get("results.updatable"));
			return redirect(callShow(t.id));
		}
		return null;
	}

	@Transactional(readOnly = true)
	public Result pdfify(Long id) {
		if (DAO == null)
			return Helper.getInternalServerError();
		models.dynamicforms.Results t = DAO.findById(id);
		if (t == null)
			return Helper.getNotFound();
		return PDF.ok(pdf.render(t));
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

	@Transactional(readOnly = true)
	public Result step2() {
		Form<models.dynamicforms.Results> filledForm = form(
				models.dynamicforms.Results.class,
				models.dynamicforms.Results.Step1.class).bindFromRequest();
		if (!filledForm.hasErrors()) {
			models.dynamicforms.Results t = filledForm.get();
			Product product = Product.dao.findById(t.product.id);
			if (product == null)
				return Helper.getNotFound();
			return ok(step2.render(filledForm, product));
		}
		return badRequest(CREATE.render(filledForm));
	}

	@Transactional(readOnly = true)
	public Result step3() {
		Form<models.dynamicforms.Results> filledForm = form(
				models.dynamicforms.Results.class,
				models.dynamicforms.Results.Step2.class).bindFromRequest();
		models.dynamicforms.Results t = filledForm.get();
		Product product = Product.dao.getReference(t.product);
		if (product == null)
			return Helper.getNotFound();
		if (!filledForm.hasErrors()) {
			models.dynamicforms.Form form = models.dynamicforms.Form.dao
					.getReference(t.form);
			if (form == null)
				return Helper.getNotFound();
			return ok(step3.render(filledForm, product, form));
		}
		return badRequest(step2.render(filledForm, product));
	}

	@Override
	@Transactional
	public Result update(Long id) {
		return super.update(id);
	}
}
