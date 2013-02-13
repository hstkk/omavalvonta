package controllers;

import models.Batch;
import controllers.helpers.UserCrud;
import static play.data.Form.*;
import play.mvc.Controller;
import views.html.batches.*;

public class Batches extends Controller {

	public final static UserCrud<Batch> crud = new UserCrud<Batch>(Batch.crud,
			form(forms.Batch.class),
			null,
			create.ref(),
			page.ref(),
			show.ref(),
			routes.Batches.crud.page(1));
}
