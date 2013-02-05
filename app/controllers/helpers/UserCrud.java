package controllers.helpers;

import controllers.shib.Secured;
import controllers.shib.SessionTimeout;
import play.api.mvc.Call;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import models.User;
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
	protected Result onCreate(T t) {
		User user = getUser();
		t.user = user;
		Result result = super.onCreate(t);
		if (user == null && result != null)
			return ack(false);
		return result;
	}

	@Override
	protected Result onUpdate(T t, Long id) {
		User user = getUser();
		t.user = user;
		Result result = super.onUpdate(t, id);
		if (user == null && result != null)
			return ack(false);
		return result;
	}

	private User getUser() {
		String username = ctx().request().username();
		return User.findByEmail(username);
	}
}
