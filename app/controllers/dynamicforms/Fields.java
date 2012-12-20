package controllers.dynamicforms;

import java.util.List;

import play.*;
import play.i18n.Messages;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.db.jpa.*;

import models.dynamicforms.Field;
import models.dynamicforms.Fieldset;

// TODO: Auto-generated Javadoc
/**
 * The Class FormTypes.
 * 
 * @author Sami Hostikka <dev@01.fi>
 */
public class Fields extends Controller {

	/** The Constant FORM. */
	final static Form<Field> FORM = form(Field.class);

	@Transactional
	public static Result create(Long fieldsetId) {
		Call REDIRECT = controllers.dynamicforms.routes.Fieldsets.crud.edit(fieldsetId);
		Form<Field> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", Messages.get("crud.cancel"));
			return redirect(REDIRECT);
		} else if (!filledForm.hasErrors()) {
			Field t = filledForm.get();
			t.fieldset = Fieldset.crud.findById(fieldsetId);
			if (Field.crud.create(t)) {
				flash("success", Messages.get("crud.success"));
				return redirect(REDIRECT);
			}
		}
		System.out.println(filledForm.errorsAsJson());
		flash("error", Messages.get("crud.fail"));
		return badRequest(views.html.dynamicforms.fields.create.render(filledForm, fieldsetId));
	}

	@Transactional(readOnly = true)
	public static Result fresh(Long fieldsetId) {
		return ok(views.html.dynamicforms.fields.create.render(FORM, fieldsetId));
	}

	@Transactional(readOnly = true)
	public static Result edit(Long fieldsetId, Long fieldId) {
		if(!Fieldset.crud.exists(fieldsetId))
			return notFound();
		Field t = Field.crud.findById(fieldId);
		if (t == null)
			return notFound();
		Form<Field> filledForm = FORM.fill(t);
		return ok(views.html.dynamicforms.fields.update.render(filledForm, fieldsetId, fieldId));
	}

	@Transactional
	public static Result update(Long fieldsetId, Long fieldId) {
		Call REDIRECT = routes.Fieldsets.crud.edit(fieldsetId);
		Form<Field> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", Messages.get("crud.cancel"));
			return redirect(REDIRECT);
		} else if (!filledForm.hasErrors()) {
			Field fresh = filledForm.get();
			fresh.id = fieldId;
			fresh.fieldset = Fieldset.crud.findById(fieldsetId);
			if (Field.crud.update(fresh)) {
				flash("success", Messages.get("crud.success"));
				return redirect(REDIRECT);
			}
		}
		flash("error", Messages.get("crud.fail"));
		return badRequest(views.html.dynamicforms.fields.update.render(filledForm, fieldsetId, fieldId));
	}
}
