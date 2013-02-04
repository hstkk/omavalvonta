package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import models.User;
import controllers.routes;
import controllers.shib.Shibboleth;
import play.Play;
import play.mvc.Http.Context;
import play.mvc.Result;

public class ShibbolethHelper {
	public static void clearSession(Context ctx) {
		ctx.session().clear();
	}

	public static void createSession(Context ctx, User user) {
		ctx.session().put("u", user.email);
		ctx.session().put("t", String.valueOf(System.currentTimeMillis()));
	}

	public static String getLoginUrl(Context ctx, String returnUrl)
			throws UnsupportedEncodingException {
		StringBuilder url = new StringBuilder();

		// Shibboleth login url
		url.append(getOrElse("shibboleth.login.url",
				ShibbolethDefaults.LOGIN_URL));

		// The IdP to request authentication from
		String entity = getOrElse("shibboleth.entityID");
		if (entity != null) {
			url.append("?entityID=");
			url.append(URLEncoder.encode(entity,
					ShibbolethDefaults.URL_ENCODING));
		}

		// The URL to return the user to after authenticating
		url.append("?target=");
		Boolean secure = true;
		url.append(URLEncoder.encode(controllers.shib.routes.Shibboleth
				.authenticate().absoluteURL(ctx.request(), secure).toString(),
				ShibbolethDefaults.URL_ENCODING));

		// Url redirect
		if (returnUrl == null)
			returnUrl = ShibbolethDefaults.HOME;
		url.append("?return=");
		url.append(URLEncoder
				.encode(returnUrl, ShibbolethDefaults.URL_ENCODING));

		return url.toString();
	}

	public static String getLogoutUrl(Context ctx)
			throws UnsupportedEncodingException {
		StringBuilder url = new StringBuilder();

		// Shibboleth logout url
		url.append(getOrElse("shibboleth.logout.url",
				ShibbolethDefaults.LOGOUT_URL));

		url.append("?return=");
		boolean secure = true;
		url.append(URLEncoder.encode(
				routes.Application.index().absoluteURL(ctx.request(), secure)
						.toString(), ShibbolethDefaults.URL_ENCODING));
		return url.toString();
	}

	public static String getOrElse(String key) {
		return Play.application().configuration().getString(key);
	}

	public static String getOrElse(String key, String defaultValue) {
		String value = getOrElse(key);
		if (value == null)
			return defaultValue;
		return value;
	}

	public static String getRedirectUrl(Context ctx, User user) {
		String url = ctx.flash().get("url");
		if (url != null)
			return url;
		String[] urls = ctx.request().queryString().get("url");
		if (urls != null && urls.length > 0 && urls[0] != null)
			return urls[0];
		// if(user != null)
		// return routes.Users.crud.show(user.id).toString();
		return ShibbolethDefaults.HOME;
	}

	public static boolean isSession(Context ctx) {
		return ctx.session().containsKey("u") && ctx.session().containsKey("t");
	}

	public static boolean isSessionValid(Context ctx, String user) {
		if (!ctx.session().isEmpty()) {
			Long timestamp = getTimestamp(ctx);
			if (user != null && timestamp != null) {
				Long maxAge = Play.application().configuration()
						.getMilliseconds("shibboleth.session.maxAge");
				if (maxAge == null)
					maxAge = ShibbolethDefaults.SESSION_MAX_AGE;
				timestamp += maxAge;
				if (timestamp > System.currentTimeMillis())
					return true;
			}
		}
		return false;
	}

	public static boolean isSessionValid(Context ctx) {
		return isSessionValid(ctx, getUsername(ctx));
	}

	public static void verifyAttributes(Context ctx) {
		// TODO Auto-generated method stub

	}

	public static String getUsername(Context ctx) {
		return ctx.session().get("u");
	}

	public static Long getTimestamp(Context ctx) {
		try {
			return Long.parseLong(ctx.session().get("t"));
		} catch (Exception e) {
		}
		return null;
	}
}
