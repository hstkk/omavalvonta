package controllers.dynamicforms;

import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import play.mvc.Result;

import views.html.*;

//TODO authenticate
public class Manage extends Controller {

	final static Form<models.dynamicforms.Form> manageForm = form(models.dynamicforms.Form.class);
	final static Form<models.dynamicforms.Field> fieldForm = form(models.dynamicforms.Field.class);

	/**
	 * Renders list of all forms.
	 */
	@Transactional(readOnly = true)
	public static Result allForms() {
		return ok(views.html.dynamicforms.allForms
				.render(models.dynamicforms.Form.findAllActive()));
	}

	/**
	 * Renders dynamic form creation form.
	 */
	public static Result createForm() {
		return ok(views.html.dynamicforms.manageForm.render(manageForm, null));
	}

	/**
	 * Saves dynamic form to database and renders edit form page if form has not errors else renders 
	 */
	@Transactional
	public static Result saveForm() {
		Form<models.dynamicforms.Form> filledManageForm = manageForm
				.bindFromRequest();
		if (!filledManageForm.hasErrors()) {
			models.dynamicforms.Form form = filledManageForm.get();
			if (form.save()) {
				flash("status", "Lomake on luotu onnistuneesti!");
				return redirect(controllers.dynamicforms.routes.Manage
						.editForm(form.id));
			}
		}
		flash("status", "Lomakkeen luonti ei onnistunut!");
		return badRequest(views.html.dynamicforms.manageForm.render(
				filledManageForm));
	}

	@Transactional(readOnly = true)
	public static Result editForm(Long formId) {
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(formId);
		if (form == null)
			return notFound(views.html.notFound.render());
		return ok(views.html.dynamicforms.manageForm.render(
				form));
	}

	/*@Transactional
	public static Result updateForm(Long formId) {
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(formId);
		if (form == null)
			return notFound(views.html.notFound.render());
		manageForm.bindFromRequest();
		if (!manageForm.hasErrors()) {
			form.set(manageForm.get());
			if (form.update()) {
				flash("status", "Lomake on päivitetty onnistuneesti!");
				return ok(views.html.dynamicforms.manage.render(manageForm,
						fieldForm, null));
			}
		}
		flash("status", "Lomakkeen päivitys ei onnistunut!");
		return badRequest(views.html.dynamicforms.manage.render(manageForm,
				fieldForm, null));
	}*/

	// TODO deleteFields
	@Transactional
	public static Result deleteForm(String formId) {
		Long id;
		try{
			id = Long.parseLong(formId);
		}catch(Exception e){
			return notFound(views.html.notFound.render());
		}
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(id);
		if (form == null)
			return notFound(views.html.notFound.render());
		if (form.delete())
			flash("status", "Lomake on poistettu onnistuneesti!");
		else
			flash("status", "Lomakkeen poisto ei onnistunut!");
		return redirect(controllers.dynamicforms.routes.Manage.allForms());
	}

	@Transactional
	public static Result saveField(Long formId) {
		models.dynamicforms.Form f = models.dynamicforms.Form.findById(formId);
		if (f == null)
			return notFound(views.html.notFound.render());
		Form<models.dynamicforms.Field> filledFieldForm = fieldForm
				.bindFromRequest();
		if (!filledFieldForm.hasErrors()) {
			models.dynamicforms.Field field = filledFieldForm.get();
			if (field.save()) {
				flash("status", "Kenttä on luotu onnistuneesti!");
				return redirect(controllers.dynamicforms.routes.Manage
						.editForm(formId));
			}
		}
		flash("status", "Kentän luonti ei onnistunut!");
		return badRequest(views.html.dynamicforms.manageField.render(
				f, models.dynamicforms.Field.findByForm(f), filledFieldForm));
	}

	@Transactional(readOnly = true)
	public static Result editField(Long formId, Long fieldId) {
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(formId);
		if (form == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Field field = models.dynamicforms.Field
				.findByFormAndId(form, fieldId);
		if (field == null)
			return notFound(views.html.notFound.render());
		return ok(views.html.dynamicforms.manage.render(
				form, models.dynamicforms.Field.findByForm(form),
				fieldForm.fill(new forms.dynamicforms.Field(field))));
	}

	/*@Transactional
	public static Result updateField(Long formId, Long fieldId) {
		models.dynamicforms.Form f = models.dynamicforms.Form.findById(formId);
		if (f == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Field field = models.dynamicforms.Field
				.findByFormAndId(f, fieldId);
		if (field == null)
			return notFound(views.html.notFound.render());
		fieldForm.bindFromRequest();
		if (!fieldForm.hasErrors()) {
			field.set(fieldForm.get());
			if (field.update()) {
				flash("status", "Kenttä on päivitetty onnistuneesti!");
				return ok(views.html.dynamicforms.manage.render(
						manageForm.fill(new forms.dynamicforms.Manage(f)),
						form(forms.dynamicforms.Field.class), null));
			}
		}
		flash("status", "Kentän päivitys ei onnistunut!");
		return badRequest(views.html.dynamicforms.manage.render(
				manageForm.fill(new forms.dynamicforms.Manage(f)), fieldForm,
				null));
	}*/

	@Transactional
	public static Result deleteField(Long formId, String fieldId) {
		Long id;
		try{
			id = Long.parseFloat(fieldId);
			Form deleteForm = form().bindFromRequest();
			if(deleteForm.field("isConfirmed").valueOr("").isEmpty() || Boolean.parseBoolean(deleteForm.field("isConfirmed").value()))
				throw new Exception();
		}catch(Exception e){
			return notFound(views.html.notFound.render());
		}
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(formId);
		if (form == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Field field = models.dynamicforms.Field
				.findByFormAndId(form, id);
		if (field == null)
			return notFound(views.html.notFound.render());
		if (field.delete())
			flash("status", "Kenttä on poistettu onnistuneesti!");
		else
			flash("status", "Kentän poisto ei onnistunut!");
		return redirect(controllers.dynamicforms.routes.Manage.editForm(formId));
	}
}