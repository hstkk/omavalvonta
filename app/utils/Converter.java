package utils;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import play.Logger;
import play.Play;
import play.i18n.Messages;

/**
 * @author Sami Hostikka
 */
public class Converter {

	public static Long stringToLong(String value) {
		try {
			return Long.parseLong(value);
		} catch (Exception e) {
			return null;
		}
	}

	public static Integer stringToInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return null;
		}
	}

	public static Boolean stringToBool(String value) {
		try {
			return Boolean.parseBoolean(value);
		} catch (Exception e) {
			return null;
		}
	}

	public static Double stringToDouble(String value) {
		return stringToDouble(value, null);
	}

	public static Double stringToDouble(String value, Locale locale) {
		try {
			if (locale == null)
				locale = Locale.getDefault();
			NumberFormat numberFormat = NumberFormat.getInstance(locale);
			Number number = numberFormat.parse(value);
			return number.doubleValue();
		} catch (Exception e) {
			return null;
		}
	}

	public static String doubleToString(Double value) {
		return doubleToString(value, null);
	}

	public static String doubleToString(Double value, Locale locale) {
		try {
			if (locale == null)
				locale = Locale.getDefault();
			if (value == null)
				return "";
			NumberFormat numberFormat = NumberFormat.getInstance(locale);
			return numberFormat.format(value);
		} catch (Exception e) {
			return "";
		}
	}

	public static Date stringToDate(String value) {
		try {
			String format = Play.application().configuration()
					.getString("date.format");
			return new SimpleDateFormat(format).parse(value);
		} catch (Exception e) {
			return null;
		}
	}

	public static String dateToString(Date date) {
		return dateToString(date, null);
	}

	public static String dateToString(Date date, String format) {
		try {
			if (format == null || format.isEmpty())
				format = Play.application().configuration()
						.getString("date.format");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			return simpleDateFormat.format(date);
		} catch (Exception e) {
			Logger.info("date", e);
			return "";
		}
	}

	public static String timeToString(Date date) {
		String format = Play.application().configuration()
						.getString("time.format");
		return dateToString(date, format);
	}

	public static String booleanToString(Boolean value) {
		if (value == null)
			return "";
		else if (value)
			return Messages.get("boolean.true");
		return Messages.get("boolean.false");
	}
}
