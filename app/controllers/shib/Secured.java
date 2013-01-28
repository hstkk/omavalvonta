package controllers.shib;

import models.User;
import play.Play;
import play.mvc.*;
import play.mvc.Http.Context;
import utils.*;

public class Secured extends Security.Authenticator {
	@Override
	public String getUsername(Context ctx) {
		// TODO Auto-generated method stub
		return super.getUsername(ctx);
	}

	@Override
	public Result onUnauthorized(Context ctx) {
		// TODO Auto-generated method stub
		return super.onUnauthorized(ctx);
	}

	public String isLogged() {
		return null;
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
