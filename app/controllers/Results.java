package controllers;

import models.User;
import models.helpers.Dao;
import models.helpers.Page;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import static play.data.Form.*;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.mvc.Call;
import play.mvc.Result;
import play.mvc.With;
import play.mvc.Security.Authenticated;
import utils.Helper;
import views.html.results.*;
import controllers.helpers.Crud;
import controllers.shib.Secured;
import controllers.shib.Session;
import util.pdf.PDF;

@With(Session.class)
public class Results extends Crud<models.dynamicforms.Results> {
	public Results() {
		/*
		 * super(models.dynamicforms.Results.dao,
		 * form(models.dynamicforms.Results.class), create.ref(), page .ref(),
		 * show.ref(), update.ref());
		 */
		super(models.dynamicforms.Results.dao,
				form(models.dynamicforms.Results.class), null, page.ref(), show
						.ref(), update.ref());
	}

	@Authenticated(Secured.class)
	public Result ack(Long resultsId, Long resultId) {
		User user = Session.user();
		if (user != null) {
			models.dynamicforms.Result t = models.dynamicforms.Result.dao
					.findById(resultId);
			if (t == null)
				return Helper.getNotFound();
			if (t.user != null)
				return Helper.getInternalServerError();
			t.user = user;
			if (models.dynamicforms.Result.dao.update(t))
				flash("success", Messages.get("crud.success"));
			else
				flash("error", Messages.get("crud.fail"));
			return redirect(callShow(resultsId));
		}
		return Helper.getUnauthorized();
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

	@Transactional(readOnly = true)
	public Result history(Long id) {
		if (DAO == null)
			return Helper.getInternalServerError();
		models.dynamicforms.Results t = DAO.findById(id);
		if (t == null)
			return Helper.getNotFound();
		return ok(history.render(t));
	}

	@Transactional(readOnly = true)
	public Result pdfify(Long id) {
		if (DAO == null)
			return Helper.getInternalServerError();
		models.dynamicforms.Results t = DAO.findById(id);
		if (t == null)
			return Helper.getNotFound();
		return PDF.ok(pdf.render(t));
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
