package controllers.dynamicforms;

import java.util.List;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.db.jpa.*;

import models.dynamicforms.Field;

// TODO: Auto-generated Javadoc
/**
 * The Class FormTypes.
 * 
 * @author Sami Hostikka <dev@01.fi>
 */
public class Fields extends Controller {

	/** The Constant FORM. */
	final static Form<Field> FORM = form(Field.class);

	@Transactional(readOnly = true)
	public static Result create(Long formId) {
		models.dynamicforms.Form f = models.dynamicforms.Form.findById(formId);
		if (f == null)
			return notFound(views.html.notFound.render());
		List<models.dynamicforms.Field> fields = Field.findByForm(f);
		return ok(views.html.dynamicforms.fields.manage.render(FORM, f, fields));
	}

	@Transactional(readOnly = true)
	public static Result update(Long formId, Long fieldId) {
		models.dynamicforms.Form f = models.dynamicforms.Form.findById(formId);
		if (f == null)
			return notFound(views.html.notFound.render());
		Field field = Field.findByFormAndId(f, fieldId);
		if (field == null)
			return notFound(views.html.notFound.render());
		List<models.dynamicforms.Field> fields = Field.findByForm(f);
		return ok(views.html.dynamicforms.fields.manage.render(
				FORM.fill(field), f, fields));
	}

	@Transactional
	public static Result save(Long formId) {
		models.dynamicforms.Form f = models.dynamicforms.Form.findById(formId);
		if (f == null)
			return notFound(views.html.notFound.render());
		Form<Field> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Kentän tallennus peruutettu!");
			// return redirect(dynamicforms.routes.Fields.create());
			return create(formId);
		} else if (!filledForm.hasErrors()) {
			Field field = filledForm.get();
			// TODO smarter save/update
			if ((field.id != null && field.update())
					|| (field.id == null && field.save())) {
				flash("success", "Kenttä on tallennettu onnistuneesti!");
				// return redirect(routes.dynamicforms.Fields.create());
				return create(formId);
			}
		}
		flash("error", "Kentän tallennus ei onnistunut!");
		List<models.dynamicforms.Field> fields = Field.findByForm(f);
		return badRequest(views.html.dynamicforms.fields.manage.render(
				filledForm, f, fields));
	}
}
