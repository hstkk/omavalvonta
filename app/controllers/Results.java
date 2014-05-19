package controllers;

import static play.data.Form.form;

import java.util.List;

import com.google.common.base.Optional;

import models.FinalProduct;
import models.Product;
import models.User;
import models.helpers.Dao;
import models.helpers.Page;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.data.Form.Field;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.libs.F;
import play.mvc.Call;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import play.mvc.With;
import util.pdf.PDF;
import utils.Converter;
import utils.Helper;
import views.html.results.history;
import views.html.results.page;
import views.html.results.pdf;
import views.html.results.show;
import views.html.results.step1;
import views.html.results.step2;
import views.html.results.step3;
import views.html.results.update;
import controllers.helpers.Crud;
import controllers.shib.Secured;
import controllers.shib.Session;

@With(Session.class)
public class Results extends Crud<models.dynamicforms.Results> {
	public Results() {
		super(
			F.Option.<Dao<models.dynamicforms.Results, Long>>Some(models.dynamicforms.Results.dao),
			F.Option.<Form<models.dynamicforms.Results>>Some(form(models.dynamicforms.Results.class)),
			F.Option.<Template1<Form<models.dynamicforms.Results>, Html>>Some(step1.ref()),
			F.Option.<Template1<Page<models.dynamicforms.Results>, Html>>Some(page.ref()),
			F.Option.<Template1<models.dynamicforms.Results, Html>>Some(show.ref()),
			F.Option.<Template2<models.dynamicforms.Results, Form<models.dynamicforms.Results>, Html>>Some(update.ref())
		);
	}

	@Authenticated(Secured.class)
	@Transactional
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
			if (models.dynamicforms.Result.dao.update(t)) {
				flash().remove("error");
				flash("success", Messages.get("crud.success"));
			} else
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

	@Transactional
	public Result create(Long productId, Long formId) {
		if (DAO.isEmpty())
			return Helper.getInternalServerError();
		Form<models.dynamicforms.Results> filledForm = form(
				models.dynamicforms.Results.class,
				models.dynamicforms.Results.Step3.class).bindFromRequest();
		Result result = onCancel(filledForm);
		if (result != null)
			return result;
		Product product = Product.dao.getReference(productId);
		models.dynamicforms.Form form = models.dynamicforms.Form.dao
				.getReference(formId);
		if (product == null || form == null)
			return Helper.getNotFound();
		if (!filledForm.hasErrors()) {
			models.dynamicforms.Results t = filledForm.get();
			boolean success = DAO.get().create(t);
			if (success) {
				flash().remove("error");
				Call call = controllers.routes.Results.edit(t.id);
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
		if (FORM.isEmpty())
			return Helper.getInternalServerError();
		String value = request().getQueryString("tuote");
		if (value != null && !value.isEmpty()) {
			Optional<Long> product = Converter.stringToLong(value);
			if (product.isPresent())
				return redirect(controllers.routes.Results.step2(product.get()));
		}
		return ok(step1.render(FORM.get()));
	}

	@Transactional(readOnly = true)
	public Result history(Long resultsId, Long resultId) {
		models.dynamicforms.Result t = models.dynamicforms.Result
				.findByResultAndId(resultsId, resultId);
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
		if (DAO.isEmpty())
			return Helper.getInternalServerError();
		models.dynamicforms.Results t = DAO.get().findById(id);
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
	public Result step2(Long productId) {
		Product product = Product.dao.findById(productId);
		if (product == null || FORM.isEmpty())
			return Helper.getNotFound();
		return ok(step2.render(FORM.get(), product));
	}

	@Transactional(readOnly = true)
	public Result step3(Long productId) {
		Product product = Product.dao.getReference(productId);
		if (product == null)
			return Helper.getNotFound();
		Form<models.dynamicforms.Results> filledForm = form(
				models.dynamicforms.Results.class,
				models.dynamicforms.Results.Step2.class).bindFromRequest();
		if (!filledForm.hasErrors()) {
			models.dynamicforms.Results t = filledForm.get();
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

	@Override
	protected Form<models.dynamicforms.Results> validateForm(
			Form<models.dynamicforms.Results> filledForm, Long id) {
		String fieldName = "results";
		String subFieldName = "valueDouble";
		Field field = filledForm.field(fieldName);
		List<Integer> indexes = field.indexes();
		for (Integer index : indexes) {
			Field subField = field.sub("[" + index + "]." + subFieldName);
			String value = subField.valueOr("");
			if (!value.isEmpty()) {
				Optional<Double> _value = Converter.stringToDouble(value);
				if (!_value.isPresent())
					filledForm.reject(subField.name(), "error.invalid");
			}
		}
		return filledForm;
	}
}
