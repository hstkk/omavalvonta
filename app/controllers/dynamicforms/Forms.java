package controllers.dynamicforms;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.db.jpa.*;

// TODO: Auto-generated Javadoc
/**
 * The Class FormTypes.
 * 
 * @author Sami Hostikka <dev@01.fi>
 */
public class Forms extends Controller {

	/** The Constant FORM. */
	final static Form<models.dynamicforms.Form> FORM = form(models.dynamicforms.Form.class);

	/**
	 * Creates the.
	 * 
	 * @return the result
	 */
/*	@Transactional(readOnly = true)
	public static Result create() {
		return ok(views.html.dynamicforms.forms.manage.render(FORM));
	}*/

	/**
	 * Index.
	 * 
	 * @return the result
	 */
/*	@Transactional(readOnly = true)
	public static Result index() {
		return page(1);
	}*/

	/**
	 * Page.
	 * 
	 * @param index
	 *            the index
	 * @return the result
	 */
	/*@Transactional(readOnly = true)
	public static Result page(int index) {
		return ok(views.html.dynamicforms.forms.page
				.render(models.dynamicforms.Form.page(index)));
	}*/

	/**
	 * Update.
	 * 
	 * @param formTypeId
	 *            the form type id
	 * @return the result
	 */
	/*@Transactional(readOnly = true)
	public static Result update(Long formId) {
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(formId);
		if (form == null)
			return notFound();
		return ok(views.html.dynamicforms.forms.manage.render(FORM.fill(form)));
	}*/

	/**
	 * Save.
	 * 
	 * @return the result
	 */
	/*@Transactional
	public static Result save() {
		Form<models.dynamicforms.Form> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Lomakkeen tallennus peruutettu!");
			// return redirect(routes.dynamicforms.Forms.index());
			return index();
		} else if (!filledForm.hasErrors()) {
			models.dynamicforms.Form form = filledForm.get();
			// TODO smarter save/update
			if ((form.id != null && form.update())
					|| (form.id == null && form.save())) {
				flash("success", "Lomake on tallennettu onnistuneesti!");
				// return redirect(routes.dynamicforms.Forms.index());
				return index();
			}
		}
		flash("error", "Lomakkeen tallennus ei onnistunut!");
		return badRequest(views.html.dynamicforms.forms.manage
				.render(filledForm));
	}*/

	/*@Transactional(readOnly = true)
	public static Result preview(Long formId) {
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(formId);
		if (form == null || form.html == null)
			return notFound();
		return ok(views.html.dynamicforms.forms.preview.render(form));
	}*/
}
