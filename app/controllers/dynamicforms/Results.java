package controllers.dynamicforms;

import java.util.List;

import forms.dynamicforms.Fieldset;
import models.*;
import models.dynamicforms.FormType;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

public class Results extends Controller {

	final static Form<forms.dynamicforms.Dynamic> dynamicForm = form(forms.dynamicforms.Dynamic.class);

	@Transactional(readOnly = true)
	public static Result add(Long batchId, String program) {

		// TODO
		String tmp = program;

		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound(views.html.notFound.render());
		Product product = Product.findById(batch.product.id);
		if (product == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Form form = null;
		if (program.equals("pesuohjelma")) {
			program = FormType.WASHPROGRAM.toString();
			form = models.dynamicforms.Form
					.findActiveById(product.washProgram.id);
		} else if (program.equals("tuotekortti")) {
			program = FormType.PRODUCTCARD.toString();
			form = models.dynamicforms.Form
					.findActiveById(product.productCard.id);
		} else if (program.equals("puhtaustarkkailu")) {
			program = FormType.PURITYMONITORING.toString();
			form = models.dynamicforms.Form
					.findActiveById(product.purityMonitoring.id);
		} else
			return notFound(views.html.notFound.render());
		if (form == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Results results = models.dynamicforms.Results
				.findByBatchAndType(batch, program);
		String html;
		if (results == null) {
			html = form.html;
			if (html == null || html.equals(""))
				return notFound(views.html.notFound.render());
			return ok(views.html.dynamicforms.dynamic.render(batch,
					dynamicForm, tmp, html));
		}
		html = utils.Form.formify(models.dynamicforms.Result
				.findByResults(results));
		if (html == null || html.equals(""))
			return notFound(views.html.notFound.render());
		return ok(views.html.dynamicforms.dynamic.render(batch, dynamicForm,
				program, html));
	}

	//TODO
	@Transactional
	public static Result save(Long batchId, String program) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound(views.html.notFound.render());

		Form<forms.dynamicforms.Dynamic> filleddynamicForm = dynamicForm
				.bindFromRequest();
		if (filleddynamicForm.field("action").value().equals("peruuta")) {
			flash("status", "Lomakkeen tallennus peruutettu!");
			return redirect(controllers.routes.Batches.show(batchId));
		}

		List<Fieldset> values = filleddynamicForm.get().values;

		if (!filleddynamicForm.hasErrors()) {
			models.dynamicforms.Results results = new models.dynamicforms.Results(
					batch, values, utils.Form.programToType(program));
			if (results.save()) {
				flash("status", "Lomake on tallennettu onnistuneesti!");
				return redirect(controllers.dynamicforms.routes.Results.show(
						batchId, program));
			}
		}

		flash("status", "Lomakkeen tallennus ei onnistunut!");
		return badRequest(views.html.dynamicforms.dynamic.render(batch,
				filleddynamicForm, program, utils.Form.formify(values)));
	}

	//TODO
	@Transactional(readOnly = true)
	public static Result show(Long batchId, String program) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Results results = models.dynamicforms.Results
				.findByBatchAndType(batch, program);
		if (results == null)
			return notFound(views.html.notFound.render());
		return TODO;
	}

	//TODO
	@Transactional(readOnly = true)
	public static Result history(Long batchId, String program) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound(views.html.notFound.render());
		models.dynamicforms.Results results = models.dynamicforms.Results
				.findByBatchAndType(batch, program);
		if (results == null)
			return notFound(views.html.notFound.render());
		return TODO;
	}
}
