package controllers.shib;

import java.io.UnsupportedEncodingException;

import models.User;

import play.*;
import play.mvc.*;
import play.db.jpa.*;
import utils.ShibbolethHelper;

public class Shibboleth extends Controller {
	// 2.
	@Transactional
	public static Result authenticate() {
		// verify required attributes
		ShibbolethHelper.verifyAttributes(ctx());

		// TODO user
		User user = null;

		String redirectUrl = ShibbolethHelper.getRedirectUrl(ctx(), user);
		return temporaryRedirect(redirectUrl);

		//return notFound();
	}

	// 1.
	public static Result login(String returnUrl) {
		try {
			String targetUrl = controllers.routes.Application.index().absoluteURL(ctx().request(), true);
System.out.print(targetUrl);
			String loginUrl = ShibbolethHelper.getLoginUrl(returnUrl);
loginUrl = "http://127.0.0.1"+loginUrl;
			return temporaryRedirect(loginUrl);
		} catch (UnsupportedEncodingException e) {
		}
		return internalServerError();
	}

	// 3.
	public static Result logout() {
		//TODO
		ShibbolethHelper.clearSession(ctx());
		try {
			String logoutUrl = ShibbolethHelper.getLogoutUrl(ctx());
logoutUrl = "http://127.0.0.1"+logoutUrl;
			return temporaryRedirect(logoutUrl);
		} catch (UnsupportedEncodingException e) {
		}
		return internalServerError();
	}
}
