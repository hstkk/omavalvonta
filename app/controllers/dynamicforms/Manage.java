package controllers.dynamicforms;

import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import play.mvc.Result;

import views.html.*;

import utils.Converter;

/**
 * @author Sami Hostikka
 */
// TODO authenticate
public class Manage extends Controller {

	final static Form<models.dynamicforms.Form> manageForm = form(models.dynamicforms.Form.class);
	final static Form<models.dynamicforms.Field> fieldForm = form(models.dynamicforms.Field.class);
	final static Form<forms.dynamicforms.Delete> deleteForm = form(forms.dynamicforms.Delete.class);

	/**
	 * Renders list of all forms.
	 */
	@Transactional(readOnly = true)
	public static Result allForms() {
		return ok(views.html.dynamicforms.allForms
				.render(models.dynamicforms.Form.findAll()));
	}

	/**
	 * Renders dynamic form creation form.
	 */
	@Transactional(readOnly = true)
	public static Result createForm() {
		return ok(views.html.dynamicforms.manageForm.render(manageForm, deleteForm));
	}

	/**
	 * If form has no errors saves dynamic form to database.
	 */
	@Transactional
	public static Result saveForm() {
		Form<models.dynamicforms.Form> filledManageForm = manageForm
				.bindFromRequest();
		if (filledManageForm.field("action").value().equals("peruuta")) {
			flash("status", "Lomakkeen tallennus peruutettu!");
			return redirect(controllers.dynamicforms.routes.Manage.allForms());
		} else if (!filledManageForm.hasErrors()) {
			models.dynamicforms.Form form = filledManageForm.get();
			if ((form.id != null && form.update()) || form.save()) {
				flash("status", "Lomake on tallennettu onnistuneesti!");
				return redirect(controllers.dynamicforms.routes.Manage
						.createField(form.id));
			}
		}
		flash("status", "Lomakkeen tallennus ei onnistunut!");
		return badRequest(views.html.dynamicforms.manageForm
				.render(filledManageForm, deleteForm));
	}

	/**
	 * Renders edit form for dynamic form.
	 * 
	 * @param forms
	 *            id.
	 */
	@Transactional(readOnly = true)
	public static Result editForm(Long formId) {
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(formId);
		if (form == null)
			return notFound(views.html.notFound.render());
		return ok(views.html.dynamicforms.manageForm.render(manageForm
				.fill(form), deleteForm));
	}

	// TODO deleteFields and results
	@Transactional
	public static Result deleteForm(String formId) {
		Long id = Converter.stringToLong(formId);
		models.dynamicforms.Form form = models.dynamicforms.Form.findById(id);
		if (form == null)
			return notFound(views.html.notFound.render());
		Form<forms.dynamicforms.Delete> filledDeleteForm =deleteForm.bindFromRequest();
		if(filledDeleteForm.hasErrors())
			return ok(views.html.dynamicforms.manageForm.render(manageForm
					.fill(form), filledDeleteForm));
		else if(form.delete()) {
			flash("status", "Lomake on poistettu onnistuneesti!");
			return redirect(controllers.dynamicforms.routes.Manage.allForms());
		}
		flash("status", "Lomakkeen poisto ei onnistunut!");
		return redirect(controllers.dynamicforms.routes.Manage.editForm(id));
	}

	@Transactional(readOnly = true)
	public static Result createField(Long formId) {
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(formId);
		if (form == null)
			return notFound(views.html.notFound.render());
		return ok(views.html.dynamicforms.manageField.render(form,
				models.dynamicforms.Field.findByForm(form), fieldForm));
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
		return badRequest(views.html.dynamicforms.manageField.render(f,
				models.dynamicforms.Field.findByForm(f), filledFieldForm));
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
		return ok(views.html.dynamicforms.manageField.render(form,
				models.dynamicforms.Field.findByForm(form),
				fieldForm.fill(field)));
	}

	@Transactional
	public static Result deleteField(Long formId, String fieldId) {
		Long id;
		try {
			id = Long.parseLong(fieldId);
			Form deleteForm = form().bindFromRequest();
			if (deleteForm.field("isConfirmed").valueOr("").isEmpty()
					|| Boolean.parseBoolean(deleteForm.field("isConfirmed")
							.value()))
				throw new Exception();
		} catch (Exception e) {
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