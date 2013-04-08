package controllers.shib;

import models.User;
import play.mvc.*;
import play.mvc.Http.Context;
import utils.*;

public class Secured extends Security.Authenticator {
	@Override
	public String getUsername(Context ctx) {
		return ShibbolethHelper.getSessionUserEmail(ctx);
	}

	@Override
	public Result onUnauthorized(Context ctx) {
		return Shibboleth.login(ctx.request().uri().toString());
	}

	public static boolean isAdmin() {
		User user = ShibbolethHelper.getSessionUser(Context.current());
		return isAdmin(user);
	}

	public static boolean isAdmin(User user) {
		if (user != null && user.role != null) {
			String adminRole = Helper.getOrElse("shibboleth.adminRole",
					ShibbolethDefaults.ROLE);
			return ShibbolethHelper.verifyRole(user.role, adminRole);
		}
		return false;
	}
}
