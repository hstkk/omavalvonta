package controllers;

import models.Batch;
import models.Batch.Step2;
import models.Ingredient;
import models.helpers.Dao;
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
import play.mvc.With;
import play.mvc.Security.Authenticated;
import views.html.batches.*;
import controllers.helpers.Crud;
import controllers.helpers.UserCrud;
import controllers.shib.Secured;
import controllers.shib.SessionTimeout;

public class Batches extends UserCrud<Batch> {

	public Batches() {
		super(Batch.dao, null, null, null, null, null);
	}

	@Override
	public Call callPage() {
		// return controllers.routes.Batches.page(1);
		return super.callPage();
	}

	@Override
	public Call callShow(Long id) {
		// return controllers.routes.Batches.show(id);
		return super.callShow(id);
	}

	@Override
	@Authenticated(Secured.class)
	@With(SessionTimeout.class)
	public Result ack(Long id) {
		// TODO Auto-generated method stub
		return super.ack(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Result page(int pageNumber) {
		// TODO Auto-generated method stub
		return super.page(pageNumber);
	}

	@Override
	@Transactional(readOnly = true)
	public Result show(Long id) {
		// TODO Auto-generated method stub
		return super.show(id);
	}
}