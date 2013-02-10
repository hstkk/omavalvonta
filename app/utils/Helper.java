package utils;

import play.i18n.Lang;
import play.i18n.Messages;

public class Helper {
	private static Lang lang;

	private static Lang getLang() {
		if (lang == null)
			lang = Lang.preferred(Lang.availables());
		return lang;
	}

	public static String getMessage(String key) {
		try {
			return Messages.get(key);
		} catch (Exception e) {
		}
		return Messages.get(getLang(), key);
	}

	public static String getOrElse(String key) {
		return getOrElse(key, "");
	}

	public static String getOrElse(String key, String defaultValue) {
		String value = getOrElse(key);
		if (value == null)
			return defaultValue;
		return value;
	}
}
