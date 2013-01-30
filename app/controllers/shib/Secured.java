package controllers.shib;

import models.User;
import play.mvc.*;
import play.mvc.Http.Context;
import utils.*;

public class Secured extends Security.Authenticator {
	@Override
	public String getUsername(Context ctx) {
		String user = ShibbolethHelper.getUsername(ctx);
		if (ShibbolethHelper.isSessionValid(ctx, user))
			return user;
		return null;
	}

	@Override
	public Result onUnauthorized(Context ctx) {
		return Shibboleth.login(ctx.request().uri().toString());
	}

	public static boolean isAdmin(User user) {
		if (user.role != null) {
			String adminRole = ShibbolethHelper.getOrElse(
					"shibboleth.adminRole", ShibbolethDefaults.ROLE);
			return user.role.equals(adminRole);
		}
		return false;
	}
}
