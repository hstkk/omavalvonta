package controllers.dynamicforms;

import models.dynamicforms.FormType;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

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
	public static Result create() {
		return views.html.dynamicforms.forms.manage.render(FORM);
	}

	/**
	 * Index.
	 * 
	 * @return the result
	 */
	public static Result index() {
		return page(1);
	}

	/**
	 * Page.
	 * 
	 * @param index
	 *            the index
	 * @return the result
	 */
	@Transactional(readOnly = true)
	public static Result page(int index) {
		return views.html.dynamicforms.form.page
				.render(models.dynamicforms.Form.page(index));
	}

	/**
	 * Update.
	 * 
	 * @param formTypeId
	 *            the form type id
	 * @return the result
	 */
	@Transactional(readOnly = true)
	public static Result update(Long formId) {
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(formId);
		if (form == null)
			return notFound(views.html.notFound.render());
		return views.html.dynamicforms.form.manage.render(FORM.fill(form));
	}

	/**
	 * Save.
	 * 
	 * @return the result
	 */
	@Transactional
	public static Result save() {
		Form<models.dynamicforms.Form> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Lomakkeen tallennus peruutettu!");
			return redirect(routes.dynamicforms.Forms.index());
		} else if (!filledForm.hasErrors()) {
			models.dynamicforms.Form form = filledForm.get();
			// TODO smarter save/update
			if ((form.id != null && form.update())
					|| (form.id == null && form.save())) {
				flash("success", "Lomake on tallennettu onnistuneesti!");
				return redirect(routes.dynamicforms.Forms.index());
			}
		}
		flash("error", "Lomakkeen tallennus ei onnistunut!");
		return badRequest(views.html.dynamicforms.forms.manage
				.render(filledForm));
	}

	@Transactional(readOnly = true)
	public static Result preview(Long formId) {
		models.dynamicforms.Form form = models.dynamicforms.Form
				.findById(formId);
		if (form == null || form.html == null)
			return notFound(views.html.notFound.render());
		if (form.basedOn != null)
			form.basedOn = models.dynamicforms.Form.findById(form.basedOn.id);
		return ok(views.html.dynamicforms.forms.preview.render(form));
	}
}
