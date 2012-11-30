package controllers.dynamicforms;

import java.util.List;

import play.*;
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

	@Transactional(readOnly = true)
	public static Result create(Long fieldsetId) {
		Fieldset fieldset= Fieldset.findById(fieldsetId);
		if (fieldset == null)
			return notFound();
		List<models.dynamicforms.Field> fields = Field.findByFieldset(fieldset);
		return ok(views.html.dynamicforms.fields.manage.render(FORM, fieldset, fields));
	}

	@Transactional(readOnly = true)
	public static Result update(Long fieldsetId, Long fieldId) {
		Fieldset fieldset = Fieldset.findById(fieldsetId);
		if (fieldset == null)
			return notFound();
		Field field = Field.findById(fieldId);
		if (field == null)
			return notFound();
		List<models.dynamicforms.Field> fields = Field.findByFieldset(fieldset);
		return ok(views.html.dynamicforms.fields.manage.render(
				FORM.fill(field), fieldset, fields));
	}

	@Transactional
	public static Result save(Long fieldsetId) {
		Fieldset fieldset = Fieldset.findById(fieldsetId);
		if (fieldset == null)
			return notFound();
		Form<Field> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Kentän tallennus peruutettu!");
			// return redirect(dynamicforms.routes.Fields.create());
			return create(fieldsetId);
		} else if (!filledForm.hasErrors()) {
			Field field = filledForm.get();
			// TODO smarter save/update
			if ((field.id != null && field.update())
					|| (field.id == null && field.save())) {
				flash("success", "Kenttä on tallennettu onnistuneesti!");
				// return redirect(routes.dynamicforms.Fields.create());
				return create(fieldsetId);
			}
		}
		flash("error", "Kentän tallennus ei onnistunut!");
		List<models.dynamicforms.Field> fields = Field.findByFieldset(fieldset);
		return badRequest(views.html.dynamicforms.fields.manage.render(
				filledForm, fieldset, fields));
	}
}
