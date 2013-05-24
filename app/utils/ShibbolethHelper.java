package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import models.User;
import play.Logger;
import play.Play;
import play.data.validation.Validation;
import play.mvc.Http.Context;
import controllers.routes;

public class ShibbolethHelper {
	public static void clearSession(Context ctx) {
		ctx.session().clear();
	}

	public static User createOrUpdateUser(User user) {
		User existingUser = User.findByEmail(user);
		if (existingUser != null) {
			user.id = existingUser.id;
			if (User.dao.update(user))
				return user;
		} else if (User.dao.create(user))
			return user;
		return null;
	}

	public static void createSession(Context ctx, User user) {
		ctx.session().put(ShibbolethDefaults.SESSION_USER, user.email);
		ctx.session().put(ShibbolethDefaults.SESSION_TIMESTAMP,
				String.valueOf(System.currentTimeMillis()));
	}

	public static String getLoginUrl(Context ctx, String returnUrl)
			throws UnsupportedEncodingException {
		StringBuilder url = new StringBuilder();

		// Shibboleth login url
		url.append(Helper.getOrElse("shibboleth.login.url"));

		// The IdP to request authentication from
		String entity = Helper.getString("shibboleth.entityID");
		if (!entity.isEmpty()) {
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
		url.append(Helper.getOrElse("shibboleth.logout.url"));

		url.append("?return=");
		boolean secure = true;
		url.append(URLEncoder.encode(
				routes.Application.index(1).absoluteURL(ctx.request(), secure)
						.toString(), ShibbolethDefaults.URL_ENCODING));

		return url.toString();
	}

	public static String getRedirectUrl(Context ctx, User user) {
		String url = ctx.flash().get("url");
		if (url != null)
			return url;
		String[] urls = ctx.request().queryString().get("return");
		if (urls != null && urls.length > 0 && urls[0] != null)
			return urls[0];
		if (user != null)
			return controllers.routes.Users.show(user.id).toString();
		return ShibbolethDefaults.HOME;
	}

	public static User getSessionUser(Context ctx) {
		String username = getSessionUserEmail(ctx);
		User user = User.findByEmail(username);
		return user;
	}

	public static String getSessionUserEmail(Context ctx) {
		String username = getUsername(ctx);
		if (isSessionValid(ctx, username))
			return username;
		return null;
	}

	public static Long getTimestamp(Context ctx) {
		try {
			return Long.parseLong(ctx.session().get(
					ShibbolethDefaults.SESSION_TIMESTAMP));
		} catch (Exception e) {
		}
		return null;
	}

	public static String getUsername(Context ctx) {
		return ctx.session().get(ShibbolethDefaults.SESSION_USER);
	}

	public static boolean isSession(Context ctx) {
		return ctx.session().containsKey(ShibbolethDefaults.SESSION_USER)
				&& ctx.session().containsKey(
						ShibbolethDefaults.SESSION_TIMESTAMP);
	}

	public static boolean isSessionValid(Context ctx) {
		return isSessionValid(ctx, getUsername(ctx));
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

	public static User mapAttributes(Context ctx) {
		Map<String, String[]> headers = ctx.request().headers();
		if (verifyAttributes(headers)) {
			User user = new User();
			user.email = headers.get(Helper
					.getString("shibboleth.attribute.email"))[0];
			user.firstName = headers.get(Helper
					.getString("shibboleth.attribute.firstName"))[0];
			user.lastName = headers.get(Helper
					.getString("shibboleth.attribute.lastName"))[0];
			user.role = headers.get(Helper
					.getString("shibboleth.attribute.role"))[0];
			return user;
		}
		return null;
	}

	public static void printHeaders(Map<String, String[]> headers) {
		for (String key : headers.keySet())
			Logger.info(key + " " + headers.get(key)[0]);
	}

	public static boolean verifyAttributes(Map<String, String[]> headers) {
		if (Play.isDev())
			printHeaders(headers);

		if (headers.isEmpty()) {
			Logger.warn("Shibboleth: empty HTTP headers");
			return false;
		}
		String[] keys = { "shibboleth.attribute.email",
				"shibboleth.attribute.firstName",
				"shibboleth.attribute.lastName", "shibboleth.attribute.role" };
		for (String key : keys) {
			String header = Helper.getString(key);
			Boolean required = Helper.getBool(key + "Required");
			if (!headers.containsKey(header)) {
				Logger.warn("Shibboleth: couldn't find " + header
						+ " HTTP header");
				return false;
			} else if (required && headers.get(header)[0].isEmpty()) {
				Logger.warn("Shibboleth: " + header + " HTTP header is empty");
				return false;
			}
		}
		return true;
	}

	public static boolean verifyAttributes(User user) {
		return (user != null && Validation.getValidator().validate(user)
				.isEmpty());
	}

	public static boolean verifyRole(String role, List<String> adminRoles) {
		if (adminRoles != null && !adminRoles.isEmpty()) {
			String separator = Helper.getString("shibboleth.separator");
			if (separator != null && !separator.isEmpty()) {
				for (String r : role.split(separator))
					if (adminRoles.contains(r.trim()))
						return true;
			} else
				return adminRoles.contains(role.trim());
		}
		return false;
	}
}
