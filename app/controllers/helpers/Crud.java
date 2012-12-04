package controllers.helpers;

import play.*;
import play.i18n.Messages;
import play.mvc.*;
import play.api.templates.*;
import play.data.*;
import static play.data.Form.*;
import play.db.jpa.*;

public class Crud<T extends models.helpers.Model<T>> extends Controller
		implements CrudInterface {

	private final Class<T> clazz;
	private final Form<T> FORM;
	private final Template1<models.helpers.Page<T>, Html> PAGETEMPLATE;
	private final Template1<Form<T>, Html> CREATETEMPLATE;
	private final Template2<Long, Form<T>, Html> UPDATETEMPLATE;
	private final models.helpers.Crud<T, Long> CRUD;

	public Crud(Class<T> clazz,
			Template1<models.helpers.Page<T>, Html> PAGETEMPLATE,
			Template1<Form<T>, Html> CREATETEMPLATE,
			Template2<Long, Form<T>, Html> UPDATETEMPLATE) {
		this.clazz = clazz;
		this.PAGETEMPLATE = PAGETEMPLATE;
		this.CREATETEMPLATE = CREATETEMPLATE;
		this.UPDATETEMPLATE = UPDATETEMPLATE;
		this.FORM = form(this.clazz);
		this.CRUD = new models.helpers.Crud<T, Long>(this.clazz, "Fieldset");
	}

	@Override
	@Transactional(readOnly = true)
	public Result page(int index) {
		return ok(PAGETEMPLATE.render(CRUD.page(index)));
	}

	@Override
	@Transactional(readOnly = true)
	public Result fresh() {
		return ok(CREATETEMPLATE.render(FORM));
	}

	@Override
	@Transactional
	public Result create() {
		Form<T> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", Messages.get("crud.cancel"));
			// return redirect(page(1));
			return page(1);
		} else if (!filledForm.hasErrors()) {
			T t = filledForm.get();
			if (t.create(t)) {
				flash("success", Messages.get("crud.success"));
				// return redirect(routes.dynamicforms.Fieldsets.crud.page(1));
				return page(1);
			}
		}
		flash("error", Messages.get("crud.fail"));
		return badRequest(CREATETEMPLATE.render(filledForm));
	}

	// TODO
	@Override
	@Transactional(readOnly = true)
	public Result update(Long id) {
		T t = CRUD.findById(id);
		if (t == null)
			return notFound();
		Form<T> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", Messages.get("crud.cancel"));
			// return redirect(page(1));
			return page(1);
		} else if (!filledForm.hasErrors()) {
			T fresh = filledForm.get();
			fresh.id = id;
			if (fresh.update(fresh)) {
				flash("success", Messages.get("crud.success"));
				// return redirect(routes.dynamicforms.Fieldsets.crud.page(1));
				return page(1);
			}
		}
		flash("error", Messages.get("crud.fail"));
		return badRequest(UPDATETEMPLATE.render(id, filledForm));
	}

	@Override
	@Transactional
	public Result edit(Long id) {
		T t = CRUD.findById(id);
		if (t == null)
			return notFound();
		Form<T> filledForm = FORM.fill(t);
		return ok(UPDATETEMPLATE.render(id, filledForm));
	}
}
