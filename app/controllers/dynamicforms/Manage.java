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
/*
	public static Result createForm() {
		return ok(views.html.dynamicforms.manage.render(manageForm, null));
	}

	@Transactional
	public static Result saveForm() {
		manageForm.bindFromRequest();
		if (manageForm.hasErrors())
			return badRequest(views.html.dynamicforms.manage.render(manageForm,
					fieldForm));
		return redirect(routes.dynamicforms
				.editForm(new models.dynamicforms.Form(manageForm.get()).id));
	}

	@Transactional
	public static Result saveField(Long formId) {
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findActiveById(formId);
		if (form.id == null)
			return notFound(views.html.notFound.render());
		fieldForm.bindFromRequest();
		if (fieldForm.hasErrors())
			return badRequest(views.html.dynamicforms.manage.render(
					manageForm.fill(new forms.dynamicforms.Manage(form)),
					fieldForm));
		form.addField(fieldForm.get());
		return redirect(routes.dynamicforms.editForm(formId));
	}

	@Transactional(readOnly=true)
	public static Result editForm(Long formId) {

	}

	@Transactional(readOnly=true)
	public static Result editField(Long formId, Long fieldId) {

	}

	public static Result updateForm(Long formId) {

	}

	public static Result updateField(Long formId, Long fieldId) {

	}

	@Transactional(readOnly=true)
	public static Result allForms() {
		return ok(views.html.dynamicforms.all.render(models.dynamicforms.Form
				.findAllActive()));
	}

	// TODO deleteFields
	@Transactional
	public static Result deleteForm(Long formId) {
		models.dynamicforms.Form form = models.dynamicforms.Form.findById(formId);
		if (form.id != null)
			form.delete();
		return redirect(routes.dynamicforms.all());
	}

	@Transactional
	public static Result deleteField(Long formId, Long fieldId) {
		models.dynamicforms.Field field = models.dynamicforms.Field
				.findById(fieldId);
		if (field.id != null)
			field.delete();
		return redirect(routes.dynamicforms.editForm(formId));
	}*/
}