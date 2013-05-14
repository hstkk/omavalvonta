package controllers;

import models.helpers.Dao;
import models.helpers.Page;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Call;
import play.mvc.Result;
import play.mvc.With;
import views.html.results.*;
import controllers.helpers.Crud;
import controllers.shib.Session;

@With(Session.class)
public class Results extends Crud<models.dynamicforms.Results>{
	public Results() {
		super(models.dynamicforms.Results.dao, form(models.dynamicforms.Results.class), create.ref(), page.ref(), show.ref(), update.ref());
	}

	@Override
	public Call callPage() {
		return controllers.routes.Results.page(1);
	}

	@Override
	public Call callShow(Long id) {
		return controllers.routes.Results.show(id);
	}

	@Override
	@Transactional
	public Result create() {
		// TODO Auto-generated method stub
		return super.create();
	}

	@Override
	@Transactional(readOnly = true)
	public Result edit(Long id) {
		return super.edit(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Result fresh() {
		// TODO Auto-generated method stub
		return super.fresh();
	}

	@Override
	@Transactional(readOnly = true)
	public Result page(int pageNumber) {
		return super.page(pageNumber);
	}

	@Override
	@Transactional(readOnly = true)
	public Result show(Long id) {
		return super.show(id);
	}

	@Override
	@Transactional
	public Result update(Long id) {
		return super.update(id);
	}

	

}
