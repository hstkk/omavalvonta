package controllers.shib;

import models.User;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;
import utils.ShibbolethHelper;

public class Session extends Action.Simple {
	@Override
	public Result call(Context ctx) throws Throwable {
		Context.current.set(ctx);

		// timeout
		if (ShibbolethHelper.isSession(ctx)
				&& !ctx.request().method().equalsIgnoreCase("POST")
				&& !ShibbolethHelper.isSessionValid(ctx)) {
			System.out.println("Timeout");
			return Shibboleth.logout();
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