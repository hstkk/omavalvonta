package controllers.shib;

import java.io.UnsupportedEncodingException;

import models.User;
import play.Logger;
import play.i18n.Messages;
import play.mvc.*;
import play.db.jpa.*;
import utils.ShibbolethHelper;
import utils.Helper;

public class Shibboleth extends Controller {
	// 2.
	@Transactional
	public static Result authenticate() {
		User user = ShibbolethHelper.mapAttributes(ctx());
		if (ShibbolethHelper.verifyAttributes(user)) {
			user = ShibbolethHelper.createOrUpdateUser(user);
			if (user != null) {
				flash().put("success", Messages.get("shib.flash.login", user.toString()));
				ShibbolethHelper.createSession(ctx(), user);
				String redirectUrl = ShibbolethHelper.getRedirectUrl(ctx(),
						user);
				return temporaryRedirect(redirectUrl);
			}
		}
		flash().put("error", Messages.get("shib.flash.fail"));
		return Helper.getUnauthorized();
	}

	// 1.
	public static Result login(String returnUrl) {
		try {
			String loginUrl = ShibbolethHelper.getLoginUrl(ctx(), returnUrl);
			return temporaryRedirect(loginUrl);
		} catch (UnsupportedEncodingException e) {
			Logger.debug(e.getMessage(), e.getCause());
		}
		return Helper.getInternalServerError();
	}

	// 3.
	public static Result logout() {
		ShibbolethHelper.clearSession(ctx());
		try {
			String logoutUrl = ShibbolethHelper.getLogoutUrl(ctx());
			flash().put("success", Messages.get("shib.flash.logout"));
			return temporaryRedirect(logoutUrl);
		} catch (UnsupportedEncodingException e) {
			Logger.debug(e.getMessage(), e.getCause());
		}
		return Helper.getInternalServerError();
	}
}
