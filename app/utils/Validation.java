package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Handy value validations.
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
public class Validation {

	/**
	 * Tests if value >= min.
	 * 
	 * @param validate
	 *            String value
	 * @param minimum
	 *            value.
	 * @return true if value >= min else false.
	 */
	public static boolean min(double value, double min) {
		if (value >= min)
			return true;
		return false;
	}

	/**
	 * Tests if value <= max.
	 * 
	 * @param validate
	 *            String value
	 * @param maximum
	 *            value.
	 * @return true if value <= max else false.
	 */
	public static boolean max(double value, double max) {
		if (value <= max)
			return true;
		return false;
	}

	/**
	 * Tests if value is int.
	 * 
	 * @param validate
	 *            String value
	 * @return int if valid else null.
	 */
	public static Integer isInt(String value) {
		return Converter.stringToInt(value);
	}

	/**
	 * Tests if value is double.
	 * 
	 * @param validate
	 *            String value
	 * @return double if valid else null.
	 */
	public static Double isDouble(String value) {
		return Converter.stringToDouble(value);
	}

	/**
	 * Tests if string is not empty or null.
	 * 
	 * @param validate
	 *            String value
	 * @return true if valid else false.
	 */
	public static boolean isString(String value) {
		if (value == null)
			return false;
		return value.isEmpty();
	}

	/**
	 * Tests if value is boolean.
	 * 
	 * @param validate
	 *            String value
	 * @return boolean if valid else null.
	 */
	public static Boolean isBoolean(String value) {
		return Converter.stringToBool(value);
	}

	/**
	 * Tests if value is date.
	 * 
	 * @param validate
	 *            String value
	 * @return Date if valid else null.
	 */
	public static Date isDate(String value) {
		return Converter.stringToDate(value);
	}
}
