package controllers;

import models.Batch;
import models.Batch.Step2;
import models.Ingredient;
import models.helpers.Page;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.batches.*;
import controllers.helpers.Crud;
import controllers.helpers.UserCrud;
import controllers.helpers.UserRouter;

public class Batches extends Controller {
	private final static Router ROUTER = new Router();
	private final static Form<Step2> STEP2 = form(Step2.class);

	public final static UserCrud<Batch> crud = new UserCrud<Batch>(Batch.crud,
			null, ROUTER, null, null, null, null);
	public final static Step1 step1 = new Step1();

	private static class Router extends UserRouter {
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

	private static class Step1 extends Crud<Batch.Step1> {
		public Step1() {
			super(
					null,
					form(Batch.Step1.class),
					ROUTER,
					views.html.batches.step1.ref(),
					null,
					null,
					null
				);
		}

		@Override
		@Transactional(readOnly = true)
		public Result create() {
			return super.create();
		}

		@Override
		@Transactional(readOnly = true)
		public Result fresh() {
			return super.fresh();
		}

		@Override
		public Result onCreateOrUpdate(Batch.Step1 t, Long id) {
			//return temporaryRedirect(controllers.routes.Batches.step2
			//		.fresh(t.product.id));
			return TODO;
		}
	}
}