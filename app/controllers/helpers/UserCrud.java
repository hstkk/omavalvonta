package controllers.helpers;

import controllers.shib.Secured;
import controllers.shib.SessionTimeout;
import play.api.mvc.Call;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import models.helpers.Page;
import models.helpers.UserModel;

public class UserCrud<T extends UserModel> extends Crud<T> {
	public UserCrud(models.helpers.Crud<T, Long> CRUD, Form<T> FORM,
			Template2<Long, Form<T>, Html> UPDATE,
			Template1<Form<T>, Html> CREATE, Template1<Page<T>, Html> PAGE,
			Template1<T, Html> SHOW, Call REDIRECT) {
		super(CRUD, FORM, UPDATE, CREATE, PAGE, SHOW, REDIRECT);
	}

	@Security.Authenticated(Secured.class)
	@With(SessionTimeout.class)
	public Result ack(Boolean bool) {
		return TODO;
	}

	@Override
	@Transactional
	public Result create() {
		// TODO Auto-generated method stub
		return super.create();
	}

	@Override
	@Transactional
	public Result update(Long id) {
		// TODO Auto-generated method stub
		return super.update(id);
	}
}
