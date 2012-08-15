package controllers.dynamicforms;

import java.util.List;

import models.dynamicforms.Field;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

// TODO: Auto-generated Javadoc
/**
 * The Class FormTypes.
 * 
 * @author Sami Hostikka <dev@01.fi>
 */
public class Fields extends Controller {

	/** The Constant FORM. */
	final static Form<Field> FORM = form(Field.class);

	public static Result create(Long formId) {
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(formId);
		if (form == null)
			return notFound(views.html.notFound.render());
		List<Field> fields = Field.findByForm(form);
		return ok(views.html.dynamicforms.fields.manage.render(FORM, form,
				fields));
	}

	@Transactional(readOnly = true)
	public static Result update(Long formId, Long fieldId) {
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(formId);
		if (form == null)
			return notFound(views.html.notFound.render());
		Field field = Field.findByFormAndId(form, fieldId);
		if (field == null)
			return notFound(views.html.notFound.render());
		List<Field> fields = Field.findByForm(form);
		return ok(views.html.dynamicforms.fields.manage.render(
				FORM.fill(field), form, fields));
	}

	@Transactional
	public static Result save(Long formId) {
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(formId);
		if (form == null)
			return notFound(views.html.notFound.render());
		Form<Field> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Kentän tallennus peruutettu!");
			return redirect(routes.dynamicforms.Fields.create());
		} else if (!filledForm.hasErrors()) {
			Field field = filledForm.get();
			// TODO smarter save/update
			if ((field.id != null && field.update())
					|| (field.id == null && field.save())) {
				flash("success", "Kenttä on tallennettu onnistuneesti!");
				return redirect(routes.dynamicforms.Fields.create());
			}
		}
		flash("error", "Kentän tallennus ei onnistunut!");
		List<Field> fields = Field.findByForm(form);
		return badRequest(views.html.dynamicforms.fields.manage.render(
				filledForm, form, fields));
	}
}
