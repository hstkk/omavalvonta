package controllers.dynamicforms;

import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import play.mvc.Result;

import views.html.*;

//TODO authenticate
public class Manage extends Controller {

	final static Form<forms.dynamicforms.Manage> manageForm = form(forms.dynamicforms.Manage.class);
	final static Form<forms.dynamicforms.Field> fieldForm = form(forms.dynamicforms.Field.class);

	@Transactional(readOnly = true)
	public static Result allForms() {
		return ok(views.html.dynamicforms.allForms
				.render(models.dynamicforms.Form.findAllActive()));
	}

	public static Result createForm() {
		return ok(views.html.dynamicforms.manage.render(manageForm, fieldForm,
				null));
	}

	@Transactional
	public static Result saveForm() {
		Form<forms.dynamicforms.Manage> filledManageForm = manageForm
				.bindFromRequest();
		if (!filledManageForm.hasErrors()) {
			System.out.print("\n\n" + filledManageForm.get().name + "\n\n");
			models.dynamicforms.Form form = new models.dynamicforms.Form(
					filledManageForm.get());
			if (form.save()) {
				flash("status", "Lomake on luotu onnistuneesti!");
				return redirect(controllers.dynamicforms.routes.Manage
						.editForm(form.id));
			}
		}
		flash("status", "Lomakkeen luonti ei onnistunut!");
		return badRequest(views.html.dynamicforms.manage.render(
				filledManageForm, fieldForm, null));
	}

	@Transactional(readOnly = true)
	public static Result editForm(Long formId) {
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(formId);
		if (form == null)
			return notFound(views.html.notFound.render());
		return ok(views.html.dynamicforms.manage
				.render(manageForm.fill(new forms.dynamicforms.Manage(form)),
						fieldForm, null));
	}

	@Transactional
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
	}

	// TODO deleteFields
	@Transactional
	public static Result deleteForm(Long formId) {
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(formId);
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
		/*
		 * models.dynamicforms.Form f =
		 * models.dynamicforms.Form.findById(formId); if (f == null) return
		 * notFound(views.html.notFound.render()); fieldForm.bindFromRequest();
		 * if (!fieldForm.hasErrors()) { models.dynamicforms.Field field = new
		 * models.dynamicforms.Field(f, fieldForm.get()); if (field.save()) {
		 * flash("status", "Kenttä on luotu onnistuneesti!"); return
		 * ok(views.html.dynamicforms.manage.render( manageForm.fill(new
		 * forms.dynamicforms.Manage(f)),form(forms.dynamicforms.Field.class)));
		 * } } flash("status", "Kentän luonti ei onnistunut!"); return
		 * badRequest(views.html.dynamicforms.manage.render( manageForm.fill(new
		 * forms.dynamicforms.Manage(f)), fieldForm));
		 */
		return notFound(views.html.notFound.render());
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
				manageForm.fill(new forms.dynamicforms.Manage(form)),
				fieldForm.fill(new forms.dynamicforms.Field(field)), null));
	}

	@Transactional
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
				manageForm.fill(new forms.dynamicforms.Manage(f)), fieldForm, null));
	}

	@Transactional
	public static Result deleteField(Long formId, Long fieldId) {
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(formId);
		if (form == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Field field = models.dynamicforms.Field
				.findByFormAndId(form, fieldId);
		if (field == null)
			return notFound(views.html.notFound.render());
		if (field.delete())
			flash("status", "Kenttä on poistettu onnistuneesti!");
		else
			flash("status", "Kentän poisto ei onnistunut!");
		return redirect(controllers.dynamicforms.routes.Manage.editForm(formId));
	}
}