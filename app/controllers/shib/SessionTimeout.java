package controllers.shib;

import play.i18n.Messages;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;
import utils.ShibbolethHelper;

public class SessionTimeout extends Action.Simple {
	@Override
	public Result call(Context ctx) throws Throwable {
		// TODO fix "There is no HTTP Context available from here."
		/*if (ShibbolethHelper.isSession(ctx)
				&& !ctx.request().method().equalsIgnoreCase("POST")
				&& !ShibbolethHelper.isSessionValid(ctx)) {
			// ctx.flash().put("info", Messages.get("session.timeout"));
			return Shibboleth.logout();
		}*/
		return delegate.call(ctx);
	}
}
