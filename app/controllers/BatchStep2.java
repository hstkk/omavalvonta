package controllers;

import static play.data.Form.form;

import java.util.List;

import com.google.common.base.Optional;

import models.Batch;
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
import play.mvc.With;
import utils.Converter;
import utils.Helper;
import views.html.batches.step2;
import controllers.helpers.UserCrud;
import controllers.shib.Session;

@With(Session.class)
public class BatchStep2 extends UserCrud<Batch> {
	public BatchStep2() {
		super(
			F.Option.<Dao<Batch, Long>>None(),
			F.Option.<Form<Batch>>Some(form(Batch.class)),
			F.Option.<Template1<Form<Batch>, Html>>None(),
			F.Option.<Template1<Page<Batch>, Html>>None(),
			F.Option.<Template1<Batch, Html>>None(),
			F.Option.<Template2<Batch, Form<Batch>, Html>>None()
		);
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
		filledForm = validateForm(filledForm);
		if (!filledForm.hasErrors()) {
			Batch batch = filledForm.get();
			User user = Session.user();
			boolean success = batch.bind(user, product);
			if (success) {
				success = Batch.dao.create(batch);
				if (success) {
					flash().remove("error");
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

	@Override
	protected Form<Batch> validateForm(Form<Batch> filledForm) {
		String fieldName = "ingredientSupplies";
		String subFieldName = "amount";
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