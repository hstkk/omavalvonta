package utils;

import play.Play;

public class ShibbolethHelper {
	public static void clearSession() {

	}

	public static void createSession() {

	}

	public static String getOrElse(String key, String defaultValue) {
		String value = Play.application().configuration().getString(key);
		if (value == null)
			return defaultValue;
		return value;
	}

	public static String urlBuilder() {
		return null;
	}
}
