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
public class FormTypes extends Controller {

	/** The Constant FORM. */
	final static Form<FormType> FORM = form(FormType.class);

	/**
	 * Creates the.
	 * 
	 * @return the result
	 */
	public static Result create() {
		return views.html.dynamicforms.formtypes.manage.render(FORM);
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
		return views.html.dynamicforms.formtypes.page.render(FormType
				.page(index));
	}

	/**
	 * Update.
	 * 
	 * @param formTypeId
	 *            the form type id
	 * @return the result
	 */
	@Transactional(readOnly = true)
	public static Result update(Long formTypeId) {
		FormType formType = FormType.findById(formTypeId);
		if (formType == null)
			return notFound(views.html.notFound.render());
		return views.html.dynamicforms.formtypes.manage.render(FORM
				.fill(formType));
	}

	/**
	 * Save.
	 * 
	 * @return the result
	 */
	@Transactional
	public static Result save() {
		Form<FormType> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Lomaketyypin tallennus peruutettu!");
			return redirect(routes.dynamicforms.FormTypes.index());
		} else if (!filledForm.hasErrors()) {
			FormType formType = filledForm.get();
			// TODO smarter save/update
			if ((formType.id != null && formType.update())
					|| (formType.id == null && formType.save())) {
				flash("success", "Lomaketyyppi on tallennettu onnistuneesti!");
				return redirect(routes.dynamicforms.FormTypes.index());
			}
		}
		flash("error", "Lomaketyypin tallennus ei onnistunut!");
		return badRequest(views.html.dynamicforms.formtypes.manage
				.render(filledForm));
	}
}
