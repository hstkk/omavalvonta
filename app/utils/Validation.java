package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Validation {

	public static boolean min(double value, double min) {
		if (value >= min)
			return true;
		return false;
	}

	public static boolean max(double value, double max) {
		if (value <= max)
			return true;
		return false;
	}

	public static Integer isInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return null;
		}
	}

	public static Double isDouble(String value) {
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean isString(String value) {
		return value.isEmpty();
	}

	public static Boolean isBoolean(String value) {
		try {
			return Boolean.parseBoolean(value);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date isDate(String value) {
		try {
			//TODO use locale
			return new SimpleDateFormat("dd.mm.yyyy").parse(value);
		} catch (Exception e) {
			return null;
		}
	}
}
