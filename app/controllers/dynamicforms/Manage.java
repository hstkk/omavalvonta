package controllers.dynamicforms;

import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import play.mvc.Result;

import views.html.*;

//TODO auhenticate
public class Manage extends Controller {

	final static Form<forms.dynamicforms.Manage> manageForm = form(forms.dynamicforms.Manage.class);
	final static Form<forms.dynamicforms.Field> fieldForm = form(forms.dynamicforms.Field.class);

	public static Result create() {
		return ok(views.html.dynamicforms.manage.render(manageForm, fieldForm));
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

	public static Result saveField(Long id) {
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findActiveById(id);
		if (form.id == null)
			return notFound(views.html.notFound.render());
		fieldForm.bindFromRequest();
		if (fieldForm.hasErrors())
			return badRequest(views.html.dynamicforms.manage.render(
					manageForm.fill(new forms.dynamicforms.Manage(form)),
					fieldForm));
		form.addField(fieldForm.get());
		return redirect(routes.dynamicforms.editForm(id));
	}

	public static Result editForm(Long id) {

	}

	public static Result editField(Long id) {

	}

	public static Result updateForm() {

	}

	public static Result updateField() {

	}

	public static Result all() {
		return ok(views.html.dynamicforms.all.render(models.dynamicforms.Form
				.findAllActive()));
	}

	// TODO deleteFields
	@Transactional
	public static Result deleteForm(Long id) {
		models.dynamicforms.Form form = models.dynamicforms.Form.findById(id);
		if (form.id != null)
			form.delete();
		return redirect(routes.dynamicforms.all());
	}

	@Transactional
	public static Result deleteField(Long id) {
		models.dynamicforms.Field field = models.dynamicforms.Field
				.findById(id);
		if (field.id != null)
			field.delete();
		return redirect(routes.dynamicforms.editForm(id));
	}
}