package utils;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.text.ParseException;
import java.util.Locale;

import com.google.common.base.Optional;

import play.data.format.Formatters.AnnotationFormatter;

public final class Formats {
	@Target({ FIELD })
	@Retention(RUNTIME)
	@play.data.Form.Display(name = "format.localizedDouble")
	public static @interface LocalizedDouble {
	}

	public static class DoubleFormatter extends
			AnnotationFormatter<LocalizedDouble, Double> {

		@Override
		public Double parse(LocalizedDouble annotation, String value,
				Locale locale) throws ParseException {
			return Converter.stringToDouble(value, Optional.fromNullable(locale)).get();
		}

		@Override
		public String print(LocalizedDouble annotation, Double value,
				Locale locale) {
			return Converter.doubleToString(value, Optional.fromNullable(locale));
		}
	}
}
