package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import play.Play;

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
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date stringToDate(String value) {
		try {
			String format = Play.application().configuration().getString("date.format");
			return new SimpleDateFormat(format).parse(value);
		} catch (Exception e) {
			return null;
		}
	}

	public static String dateToString(Date date) {
		String format = Play.application().configuration().getString("date.format");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}
}
