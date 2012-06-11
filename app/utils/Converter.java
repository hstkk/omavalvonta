package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

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
			// TODO use locale
			return new SimpleDateFormat("dd.mm.yyyy").parse(value);
		} catch (Exception e) {
			return null;
		}
	}
}
