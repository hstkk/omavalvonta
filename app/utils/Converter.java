package utils;

/**
 * @author Sami Hostikka
 */
public class Converter {

	public static Long stringToLong(String string) {
		try {
			return Long.parseLong(string);
		} catch (Exception e) {
			return null;
		}
	}
}
