package controllers.helpers;

import models.User;
import models.helpers.Dao;
import models.helpers.Page;
import models.helpers.UserModel;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.libs.F;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import play.mvc.With;
import utils.Helper;
import com.google.common.base.Optional;
import controllers.shib.Secured;
import controllers.shib.Session;

@With(Session.class)
public class UserCrud<T extends UserModel> extends Crud<T> {

	public UserCrud(
			F.Option<Dao<T, Long>> DAO,
			F.Option<Form<T>> FORM,
			F.Option<Template1<Form<T>, Html>> CREATE,
			F.Option<Template1<Page<T>, Html>> PAGE,
			F.Option<Template1<T, Html>> SHOW,
			F.Option<Template2<T, Form<T>, Html>> UPDATE
		) {
		super(DAO, FORM, CREATE, PAGE, SHOW, UPDATE);
	}

	@Authenticated(Secured.class)
	@Transactional
	public Result ack(Long id) {
		if(DAO.isEmpty())
			return Helper.getNotFound();
		User user = Session.user();
		if (Optional.fromNullable(user).isPresent()) {
			Optional<T> t = DAO.get().findById(id);
			if (!t.isPresent())
				return Helper.getNotFound();
			if (Optional.fromNullable(t.get().user).isPresent())
				return Helper.getInternalServerError();
			T tUpdate = t.get();
			tUpdate.user = user;
			if (DAO.get().update(tUpdate)) {
				flash().remove("error");
				flash("success", Messages.get("crud.success"));
			} else
				flash("error", Messages.get("crud.fail"));
			return redirect(callShow(id));
		}
		return Helper.getUnauthorized();
	}

	@Override
	@Transactional
	public Result create() {
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
		return super.fresh();
	}

	@Override
	public Optional<Result> onCreateOrUpdate(T t, Optional<Long> id) {
		User user = Session.user();
		t.user = user;
		return super.onCreateOrUpdate(t, id);
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
