package controllers.helpers;

import java.lang.reflect.Field;

import models.dynamicforms.Fieldset;
import play.*;
import play.mvc.*;
import play.api.templates.*;
import play.data.*;
import static play.data.Form.*;
import play.db.jpa.*;

public class Crud<T> extends Controller implements CrudInterface {

	private final Class<T> clazz;
	private final models.helpers.Crud<T, Long> CRUD;
	private final Form<T> FORM;
	private final Template1<models.helpers.Page<T>, Html> PAGETEMPLATE;
	private final Template1<Form<T>, Html> CREATETEMPLATE;
	private final Template2<Long, Form<T>, Html> UPDATETEMPLATE;

	public Crud(Class<T> clazz, models.helpers.Crud<T, Long> CRUD,
			Form<T> FORM, Template1<models.helpers.Page<T>, Html> PAGETEMPLATE,
			Template1<Form<T>, Html> CREATETEMPLATE,
			Template2<Long, Form<T>, Html> UPDATETEMPLATE) {
		this.clazz = clazz;
		this.CRUD = CRUD;
		this.FORM = FORM;
		this.PAGETEMPLATE = PAGETEMPLATE;
		this.CREATETEMPLATE = CREATETEMPLATE;
		this.UPDATETEMPLATE = UPDATETEMPLATE;
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
			flash("warning", "Tallennus peruutettu!");
			// return redirect(page(1));
			return page(1);
		} else if (!filledForm.hasErrors()) {
			T t = filledForm.get();
			if (CRUD.create(t)) {
				flash("success", "Tallennus onnistui!");
				// return redirect(routes.dynamicforms.Fieldsets.crud.page(1));
				return page(1);
			}
		}
		flash("error", "Tallennus epäonnistui!");
		return badRequest(CREATETEMPLATE.render(filledForm));
	}

	// TODO
	@Override
	@Transactional(readOnly = true)
	public Result update(Long id) {
		T old = CRUD.findById(id);
		if (old == null)
			return notFound();
		Form<T> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Tallennus peruutettu!");
			// return redirect(page(1));
			return page(1);
		} else if (!filledForm.hasErrors()) {
			T fresh = filledForm.get();
			/*try {
				Class<?> c = t.getClass();
				java.lang.reflect.Field field = c.getField("id");
				 field.setAccessible(true);
				field.setLong(c, id);
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			try {
				org.apache.commons.beanutils.BeanUtils.copyProperties(fresh, old);
			} catch (Exception e) {
			}
			if (CRUD.update(fresh)) {
				flash("success", "Tallennus onnistui!");
				// return redirect(routes.dynamicforms.Fieldsets.crud.page(1));
				return page(1);
			}
		}
		flash("error", "Tallennus epäonnistui!");
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
