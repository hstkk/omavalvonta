package controllers.shib;

import java.io.UnsupportedEncodingException;

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

		//TODO user

		//TODO redirect

		return notFound();
	}

	// 1.
	public static Result login(String returnUrl) {
		try {
			String loginUrl = ShibbolethHelper.getLoginUrl(ctx(), returnUrl);
			return temporaryRedirect(loginUrl);
		} catch (UnsupportedEncodingException e) {
		}
		return internalServerError();
	}

	// 3.
	public static Result logout() {
		ShibbolethHelper.clearSession(ctx());
		try {
			String logoutUrl = ShibbolethHelper.getLogoutUrl();
			return temporaryRedirect(logoutUrl);
		} catch (UnsupportedEncodingException e) {
		}
		return internalServerError();
	}
}
