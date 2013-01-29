package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.MapType;

import models.User;
import controllers.routes;
import controllers.shib.Shibboleth;
import static play.libs.Json.toJson;
import play.Play;
import play.mvc.Http.Context;
import play.mvc.Result;

public class ShibbolethHelper {
	public static void clearSession(Context ctx) {
		ctx.session().clear();
	}

	public static void createSession(Context ctx, User user) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(ShibbolethDefaults.SESSION_EMAIL, user.email);
		map.put(ShibbolethDefaults.SESSION_TIMESTAMP,
				String.valueOf(System.currentTimeMillis()));
		String json = toJson(map).toString();
		ctx.session().put(ShibbolethDefaults.SESSION_NAME, json);
	}

	public static String getLoginUrl(/* Context ctx */String targetUrl,
			String returnUrl) throws UnsupportedEncodingException {
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
		Boolean secure = true;
		url.append("?target=");
		url.append(URLEncoder
				.encode(targetUrl, ShibbolethDefaults.URL_ENCODING));
		/*
		 * url.append(URLEncoder.encode(
		 * routes.Application.index().absoluteURL(ctx.request(), secure)
		 * .toString(), ShibbolethDefaults.URL_ENCODING));
		 */

		// Url redirect
		if (returnUrl == null)
			returnUrl = ShibbolethDefaults.HOME;
		url.append("?url=");
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

	public static Map<String, String> getSession(Context ctx) {
		String cookie = ctx.session().get(ShibbolethDefaults.SESSION_NAME);
		if (cookie != null)
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				MapType mapType = objectMapper.getTypeFactory()
						.constructMapType(HashMap.class, String.class,
								String.class);
				Map<String, String> session = objectMapper.readValue(cookie,
						mapType);
				return session;
			} catch (Exception e) {
			}
		return null;
	}

	public static boolean isSession(Context ctx) {
		return ctx.session().containsKey(ShibbolethDefaults.SESSION_NAME);
	}

	public static boolean isSessionValid(Map<String, String> session) {
		if (session != null) {
			String email = getUsername(session);
			Long timestamp = getTimestamp(session);
			if (email != null && timestamp != null) {
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
		Map<String, String> session = getSession(ctx);
		return isSessionValid(session);
	}

	public static Result verifySession(Map<String, String> session) {
		if (isSessionValid(session))
			return null;
		return Shibboleth.logout();
	}

	public static void verifyAttributes(Context ctx) {
		// TODO Auto-generated method stub

	}

	public static String getUsername(Map<String, String> session) {
		if (session != null
				&& session.containsKey(ShibbolethDefaults.SESSION_EMAIL))
			return session.get(ShibbolethDefaults.SESSION_EMAIL);
		return null;
	}

	public static Long getTimestamp(Map<String, String> session) {
		if (session != null
				&& session.containsKey(ShibbolethDefaults.SESSION_TIMESTAMP))
			try {
				String timestamp = session
						.get(ShibbolethDefaults.SESSION_TIMESTAMP);
				return Long.parseLong(timestamp);
			} catch (Exception e) {
			}
		return null;
	}
}
