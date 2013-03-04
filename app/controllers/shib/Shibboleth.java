package controllers.shib;

import static play.mvc.Results.internalServerError;

import java.io.UnsupportedEncodingException;

import akka.util.Helpers;

import models.User;

import play.*;
import play.i18n.Messages;
import play.mvc.*;
import play.db.jpa.*;
import utils.ShibbolethHelper;
import utils.Helper;

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

		// return notFound();
	}

	// 1.
	public static Result login(String returnUrl) {
		/*try {
			String loginUrl = ShibbolethHelper.getLoginUrl(ctx(), returnUrl);
			return temporaryRedirect(loginUrl);
		} catch (UnsupportedEncodingException e) {
		}*/
		return getInternalServerError();
	}

	// 3.
	public static Result logout() {
		ShibbolethHelper.clearSession(ctx());
		try {
			String logoutUrl = ShibbolethHelper.getLogoutUrl(ctx());
			flash().put("success", Messages.get("shib.flash.logout"));
			return temporaryRedirect(logoutUrl);
		} catch (UnsupportedEncodingException e) {
		}
		return getInternalServerError();
	}

	private static Result getInternalServerError() {
		String title = Helper.getMessage("http.500");
		String description = Helper.getMessage("http.500.description");
		return internalServerError(views.html.error.render(title, description));
	}
}
