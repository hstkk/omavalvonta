package controllers.shib;

import java.util.Map;

import controllers.routes;
import models.User;
import play.Play;
import play.mvc.*;
import play.mvc.Http.Context;
import utils.*;

public class Secured extends Security.Authenticator {
	@Override
	public String getUsername(Context ctx) {
		Map<String, String> session = ShibbolethHelper.getSession(ctx);
		if (ShibbolethHelper.isSessionValid(session))
			return ShibbolethHelper.getUsername(session);
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
