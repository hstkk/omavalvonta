package controllers;

import models.Batch;
import models.Batch.Step1;
import models.Batch.Step2;
import models.Ingredient;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.batches.*;
import controllers.helpers.UserCrud;
import controllers.helpers.UserRouter;

public class Batches extends Controller {
	private final static Router ROUTER = new Router();
	private final static Form<Step1> STEP1 = form(Step1.class);
	private final static Form<Step2> STEP2 = form(Step2.class);

	public final static UserCrud<Batch> crud = new UserCrud<Batch>(Batch.crud,
			null, ROUTER, null, null, null, null);

	public static class Router extends UserRouter {
		@Override
		public Call ack(Long id) {
			// TODO ack url
			return super.ack(id);
		}

		@Override
		public play.mvc.Call page() {
			return controllers.routes.Batches.crud.page(1);
		}

		@Override
		public play.mvc.Call show(Long id) {
			return controllers.routes.Batches.crud.show(id);
		}
	}

	public static Result create() {
		Form<Step1> filledForm = STEP1.bindFromRequest();
		if (filledForm.field("action").value()
				.equals(Messages.get("crud.action.cancel"))) {
			flash("warning", Messages.get("crud.cancel"));
			return temporaryRedirect(ROUTER.page());
		}
		if (!filledForm.hasErrors()) {
			Step1 step1 = filledForm.get();
			temporaryRedirect(controllers.routes.Batches
					.fresh(step1.product.id));
		}
		flash("error", Messages.get("crud.fail"));
		return badRequest(step1.render(filledForm));
	}

	public static Result fresh() {
		return ok(step1.render(STEP1));
	}

	public static Result create(Long productId) {
		return null;
	}

	public static Result fresh(Long productId) {
		return null;
	}
}