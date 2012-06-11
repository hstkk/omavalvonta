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
		return ok(views.html.dynamicforms.manageForm.render(manageForm,
				deleteForm));
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
			if ((form.id != null && form.update()) || (form.id == null && form.save())) {
				flash("status", "Lomake on tallennettu onnistuneesti!");
				return redirect(controllers.dynamicforms.routes.Manage
						.createField(form.id));
			}
		}
		flash("status", "Lomakkeen tallennus ei onnistunut!");
		return badRequest(views.html.dynamicforms.manageForm.render(
				filledManageForm, deleteForm));
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
		return ok(views.html.dynamicforms.manageForm.render(
				manageForm.fill(form), deleteForm));
	}

	/**
	 * Deletes form if deletion has been confirmed.
	 * 
	 * @param forms
	 *            id
	 */
	// TODO deleteFields and results
	@Transactional
	public static Result deleteForm(String formId) {
		Long id = Converter.stringToLong(formId);
		models.dynamicforms.Form form = models.dynamicforms.Form.findById(id);
		if (form == null)
			return notFound(views.html.notFound.render());
		Form<forms.dynamicforms.Delete> filledDeleteForm = deleteForm
				.bindFromRequest();
		if (filledDeleteForm.hasErrors())
			return ok(views.html.dynamicforms.manageForm.render(
					manageForm.fill(form), filledDeleteForm));
		else if (form.delete()) {
			flash("status", "Lomake on poistettu onnistuneesti!");
			return redirect(controllers.dynamicforms.routes.Manage.allForms());
		}
		flash("status", "Lomakkeen poisto ei onnistunut!");
		return redirect(controllers.dynamicforms.routes.Manage.editForm(id));
	}

	/**
	 * Renders create field page.
	 * 
	 * @param forms
	 *            id.
	 */
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
		if (filledFieldForm.field("action").value().equals("peruuta")) {
			flash("status", "Kentän tallennus peruutettu!");
			return badRequest(views.html.dynamicforms.manageField.render(f,
					models.dynamicforms.Field.findByForm(f), fieldForm));
		} else if (!filledFieldForm.hasErrors()) {
			models.dynamicforms.Field field = filledFieldForm.get();
			if ((field.id != null && field.update()) || (field.id == null && field.save())) {
				flash("status", "KenttÃ¤ on tallennettu onnistuneesti!");
				return redirect(controllers.dynamicforms.routes.Manage
						.createField(formId));
			}
		}
		flash("status", "KentÃ¤n tallennus ei onnistunut!");
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
		Long id = Converter.stringToLong(fieldId);
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(formId);
		if (form == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Field field = models.dynamicforms.Field
				.findByFormAndId(form, id);
		if (field == null)
			return notFound(views.html.notFound.render());
		Form<forms.dynamicforms.Delete> filledDeleteForm = deleteForm
				.bindFromRequest();
		if (filledDeleteForm.hasErrors())
			//TODO
			return ok(views.html.dynamicforms.manageForm.render(
					manageForm.fill(form), filledDeleteForm));
		else if (form.delete()) {
			flash("status", "Lomake on poistettu onnistuneesti!");
			return redirect(controllers.dynamicforms.routes.Manage.allForms());
		}
		flash("status", "Lomakkeen poisto ei onnistunut!");
		return redirect(controllers.dynamicforms.routes.Manage.editForm(id));

	}
}