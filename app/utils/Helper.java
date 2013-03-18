package utils;

import java.util.Arrays;

import play.Play;
import play.i18n.Lang;
import play.i18n.Messages;
import play.mvc.Result;
import scala.collection.mutable.Buffer;

public class Helper {
	private static Lang lang;

	private static Lang getLang() {
		if (lang == null)
			lang = Lang.preferred(Lang.availables());
		return lang;
	}

	public static String getMessage(String key, Object... args) {
		Buffer<Object> scalaArgs = scala.collection.JavaConverters
				.asScalaBufferConverter(Arrays.asList(args)).asScala();
		try {
			return Messages.get(key, scalaArgs);
		} catch (Exception e) {
		}
		return Messages.get(getLang(), key, scalaArgs);
	}

	public static String getOrElse(String key) {
		return getOrElse(key, "");
	}

	public static String getOrElse(String key, String defaultValue) {
		String value = Play.application().configuration().getString(key);
		if (value == null)
			return defaultValue;
		return value;
	}

	public static Result getUnauthorized() {
		String title = Helper.getMessage("http.401");
		String description = Helper.getMessage("http.401.description");
		return play.mvc.Results.unauthorized(views.html.error.render(title,
				description));
	}

	public static Result getInternalServerError() {
		String title = Helper.getMessage("http.500");
		String description = Helper.getMessage("http.500.description");
		return play.mvc.Results.internalServerError(views.html.error.render(
				title, description));
	}
}
