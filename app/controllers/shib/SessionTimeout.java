package controllers.shib;

import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;
import utils.ShibbolethHelper;

public class SessionTimeout extends Action.Simple {
	@Override
	public Result call(Context ctx) throws Throwable {
		Context.current.set(ctx);
		if (ShibbolethHelper.isSession(ctx)
				&& !ctx.request().method().equalsIgnoreCase("POST")
				&& !ShibbolethHelper.isSessionValid(ctx))
			return Shibboleth.logout();
		return delegate.call(ctx);
	}
}
