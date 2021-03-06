package controllers.shib;

import models.User;
import play.i18n.Messages;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.SimpleResult;
import utils.ShibbolethHelper;

public class Session extends Action.Simple {

	@Override
	public Promise<SimpleResult> call(Context ctx) throws Throwable {
		Context.current.set(ctx);

		// timeout
		if (ShibbolethHelper.isSession(ctx)
				&& !ShibbolethHelper.isSessionValid(ctx)) {
			ctx.flash().put("info", Messages.get("shib.flash.timeout"));
			return Promise.<SimpleResult>pure((SimpleResult) Shibboleth.logout());
		}

		return delegate.call(ctx);
	}

	public static User user() {
		return ShibbolethHelper.getSessionUser(Context.current());
	}

	public static String username() {
		return ShibbolethHelper.getSessionUserEmail(Context.current());
	}
}
