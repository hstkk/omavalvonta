package utils;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.common.base.Optional;

import play.Logger;
import play.Play;
import play.i18n.Messages;

/**
 * @author Sami Hostikka
 */
public class Converter {

	public static Optional<Long> stringToLong(String value) {
		try {
			return Optional.fromNullable(Long.parseLong(value));
		} catch (Exception e) {
			return Optional.absent();
		}
	}

	public static Optional<Integer> stringToInt(String value) {
		try {
			return Optional.fromNullable(Integer.parseInt(value));
		} catch (Exception e) {
			return Optional.absent();
		}
	}

	public static Optional<Boolean> stringToBool(String value) {
		try {
			return Optional.fromNullable(Boolean.parseBoolean(value));
		} catch (Exception e) {
			return Optional.absent();
		}
	}

	public static Optional<Double> stringToDouble(String value) {
		return stringToDouble(value, Optional.<Locale>absent());
	}

	public static Optional<Double> stringToDouble(String value, Optional<Locale> locale) {
		try {
			NumberFormat numberFormat = NumberFormat.getInstance(locale.or(Locale.getDefault()));
			Number number = numberFormat.parse(value);
			return Optional.fromNullable(number.doubleValue());
		} catch (Exception e) {
			return Optional.absent();
		}
	}

	public static String doubleToString(Double value) {
		return doubleToString(value, Optional.<Locale>absent());
	}

	public static String doubleToString(Double value, Optional<Locale> locale) {
		try {
			if (!Optional.fromNullable(value).isPresent())
				return "";
			NumberFormat numberFormat = NumberFormat.getInstance(locale.or(Locale.getDefault()));
			return numberFormat.format(value);
		} catch (Exception e) {
			return "";
		}
	}

	public static Optional<Date> stringToDate(String value) {
		try {
			String format = Play.application().configuration()
					.getString("date.format");
			return Optional.fromNullable(new SimpleDateFormat(format).parse(value));
		} catch (Exception e) {
			return Optional.absent();
		}
	}

	public static String dateToString(Date date) {
		return dateToString(date, Optional.<String>absent());
	}

	public static String dateToString(Date date, Optional<String> format) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format.or(Play.application().configuration()
						.getString("date.format")));
			return simpleDateFormat.format(date);
		} catch (Exception e) {
			Logger.info("date", e);
			return "";
		}
	}

	public static String timeToString(Date date) {
		String format = Play.application().configuration()
						.getString("time.format");
		return dateToString(date, Optional.fromNullable(format));
	}

	public static String booleanToString(Boolean value) {
		if (value == null)
			return "";
		else if (value)
			return Messages.get("boolean.true");
		return Messages.get("boolean.false");
	}
}
