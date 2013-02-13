package controllers;

import models.FinalProduct;
import controllers.helpers.UserCrud;
import static play.data.Form.*;
import play.mvc.Controller;
import views.html.finalproducts.*;

import views.html.*;

public class FinalProducts extends Controller {
	public final static UserCrud<FinalProduct> crud = new UserCrud<FinalProduct>(
			FinalProduct.crud,
			form(FinalProduct.class),
			update.ref(),
			create.ref(),
			null,
			null,
			null);

	// TODO Remove
	/*@Transactional()
	public static Result save(Long batchId) {
		Batch batch = Batch.findById(batchId);
		if (batch == null)
			return notFound();
		Form<forms.FinalProduct> filledForm = FORM.bindFromRequest();
		if (filledForm.field("action").value().equals("peruuta")) {
			flash("warning", "Tallennus peruutettu!");
			return redirect(routes.FinalProducts.read(batchId));
		} else if (!filledForm.hasErrors()) {
			FinalProduct finalProduct = FinalProduct.findByBatch(batch);
			if (finalProduct == null)
				finalProduct = new FinalProduct();
			finalProduct.amount = filledForm.get().amount;
			finalProduct.comment = filledForm.get().comment;
			finalProduct.batch = batch;
			finalProduct.unit = Term.crud.findById(filledForm.get().unit.id);
			finalProduct.destiny = Term.crud.findById(filledForm.get().destiny.id);
			if (FinalProduct.crud.update(finalProduct)) {
				flash("success", "Tallennus onnistui!");
				return redirect(routes.FinalProducts.read(batchId));
			}
		}
		flash("error", "Tallennus epäonnistui!");
		return ok(views.html.finalproducts.edit.render(batch, filledForm));
	}*/
}
