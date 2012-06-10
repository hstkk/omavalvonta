package controllers.dynamicforms;

import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import play.mvc.Result;

import views.html.*;

/**
 * @author Sami Hostikka
 */
// TODO authenticate
public class Manage extends Controller {

	final static Form<models.dynamicforms.Form> manageForm = form(models.dynamicforms.Form.class);
	final static Form<models.dynamicforms.Field> fieldForm = form(models.dynamicforms.Field.class);

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
		return ok(views.html.dynamicforms.manageForm.render(manageForm));
	}

	/**
	 * If form has no errors saves dynamic form to database.
	 */
	//TODO renderöi oikea url
	@Transactional
	public static Result saveForm() {
		Form<models.dynamicforms.Form> filledManageForm = manageForm
				.bindFromRequest();
		if (filledManageForm.field("action").value().equals("peruuta")) {
			flash("status", "Toiminto peruutettu!");
			return redirect(controllers.dynamicforms.routes.Manage.allForms());
		} else if (!filledManageForm.hasErrors()) {
			models.dynamicforms.Form form = filledManageForm.get();
			flash("status", "Lomake on tallennettu onnistuneesti!");
			if (form.id != null)
				if (form.update())
					;
				else if (form.save()) {
					return redirect(controllers.dynamicforms.routes.Manage
							.editForm(form.id));
				}
		}
		flash("status", "Lomakkeen tallennus ei onnistunut!");
		return badRequest(views.html.dynamicforms.manageForm
				.render(filledManageForm));
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
				.fill(form)));
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

	/*
	 * @Transactional public static Result updateForm(Long formId) {
	 * models.dynamicforms.Form form = models.dynamicforms.Form
	 * .findById(formId); if (form == null) return
	 * notFound(views.html.notFound.render()); manageForm.bindFromRequest(); if
	 * (!manageForm.hasErrors()) { form.set(manageForm.get()); if
	 * (form.update()) { flash("status", "Lomake on päivitetty onnistuneesti!");
	 * return ok(views.html.dynamicforms.manage.render(manageForm, fieldForm,
	 * null)); } } flash("status", "Lomakkeen päivitys ei onnistunut!"); return
	 * badRequest(views.html.dynamicforms.manage.render(manageForm, fieldForm,
	 * null)); }
	 */

	// TODO deleteFields
	@Transactional
	public static Result deleteForm(String formId) {
		Long id;
		try {
			id = Long.parseLong(formId);
		} catch (Exception e) {
			return notFound(views.html.notFound.render());
		}
		models.dynamicforms.Form form = models.dynamicforms.Form.findById(id);
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

	/*
	 * @Transactional public static Result updateField(Long formId, Long
	 * fieldId) { models.dynamicforms.Form f =
	 * models.dynamicforms.Form.findById(formId); if (f == null) return
	 * notFound(views.html.notFound.render()); models.dynamicforms.Field field =
	 * models.dynamicforms.Field .findByFormAndId(f, fieldId); if (field ==
	 * null) return notFound(views.html.notFound.render());
	 * fieldForm.bindFromRequest(); if (!fieldForm.hasErrors()) {
	 * field.set(fieldForm.get()); if (field.update()) { flash("status",
	 * "Kenttä on päivitetty onnistuneesti!"); return
	 * ok(views.html.dynamicforms.manage.render( manageForm.fill(new
	 * forms.dynamicforms.Manage(f)), form(forms.dynamicforms.Field.class),
	 * null)); } } flash("status", "Kentän päivitys ei onnistunut!"); return
	 * badRequest(views.html.dynamicforms.manage.render( manageForm.fill(new
	 * forms.dynamicforms.Manage(f)), fieldForm, null)); }
	 */

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