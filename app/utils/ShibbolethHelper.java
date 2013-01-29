package utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.MapType;

import models.User;
import controllers.routes;
import controllers.shib.Shibboleth;
import static play.libs.Json.fromJson;
import static play.libs.Json.toJson;
import play.Play;
import play.libs.Json;
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
		Boolean secure = true;
		url.append("?target=");
		url.append(URLEncoder.encode(
				routes.Application.index().absoluteURL(ctx.request(), secure)
						.toString(), ShibbolethDefaults.URL_ENCODING));

		// Url redirect
		if (returnUrl == null)
			returnUrl = ShibbolethDefaults.HOME;
		url.append("?return=");
		url.append(URLEncoder
				.encode(returnUrl, ShibbolethDefaults.URL_ENCODING));

		return url.toString();
	}

	public static String getLogoutUrl() throws UnsupportedEncodingException {
		StringBuilder url = new StringBuilder();

		// Shibboleth logout url
		url.append(getOrElse("shibboleth.logout.url",
				ShibbolethDefaults.LOGOUT_URL));

		url.append("?return=");
		url.append(URLEncoder.encode(ShibbolethDefaults.HOME,
				ShibbolethDefaults.URL_ENCODING));

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

	public static Map<String, String> getSession(Context ctx) {
		String cookie = ctx.session().get(ShibbolethDefaults.SESSION_NAME);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			MapType mapType = objectMapper.getTypeFactory().constructMapType(
					HashMap.class, String.class, String.class);
			Map<String, String> session = objectMapper.readValue(cookie,
					mapType);
			return session;
		} catch (Exception e) {
		}
		return null;
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
