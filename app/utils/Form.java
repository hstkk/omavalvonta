package utils;

import java.util.*;
import models.dynamicforms.Field;
import models.dynamicforms.Result;
import forms.dynamicforms.Fieldset;

public class Form {

	// TODO label for
	public static <T> String formify(List<T> list) {
		if (list == null || list.isEmpty())
			return null;

		int i = 0;

		StringBuilder html = new StringBuilder();
		for (Object e : list) {
			Field field = null;
			String value = "", comment = "";
			if (Field.class.isAssignableFrom(e.getClass()))
				field = (Field) e;
			else if (Result.class.isAssignableFrom(e.getClass())) {
				Result result = (Result) e;
				field = Field.findById(result.field.id);
				value = result.toString();
				comment = result.comment;
			} else if (Fieldset.class.isAssignableFrom(e.getClass())) {
				Fieldset fieldset = (Fieldset) e;
				field = Field.findById(fieldset.fieldId);
				value = fieldset.value;
				comment = fieldset.comment;
			}

			if (field != null) {
				html.append("<fieldset><div class=\"control-group\"><label class=\"control-label\">");
				html.append(field.name);
				html.append("</label><div class=\"controls\">");
				switch (field.type) {
				case CHECKBOX:
					html.append("<input type=\"checkbox\" name=\"values[" + i
							+ "].value\" value=\"");
					html.append(value);
					html.append("\"/>");
					break;
				case DATE:
					html.append("<input class=\"input-xlarge\" name=\"values["
							+ i
							+ "].value\" type=\"text\" placeholder=\"pp.kk.vvvv\" value=\"");
					html.append(value);
					html.append("\"/>");
					break;
				case INT:
					html.append("<input class=\"input-xlarge\" name=\"values["
							+ i
							+ "].value\" type=\"text\" placeholder=\"0\" value=\"");
					html.append(value);
					html.append("\"/>");
					break;
				case DOUBLE:
					html.append("<input class=\"input-xlarge\" name=\"values["
							+ i
							+ "].value\" type=\"text\" placeholder=\"0,0\" value=\"");
					html.append(value);
					html.append("\"/>");
					break;
				case TEXT:
					html.append("<input class=\"input-xlarge\" name=\"values["
							+ i + "].value\" type=\"text\" value=\"");
					html.append(value);
					html.append("\"/>");
					break;
				case TEXTAREA:
					html.append("<textarea class=\"input-xlarge\" name=\"values["
							+ i + "].value\" rows=\"3\">");
					html.append(value);
					html.append("</textarea>");
					break;
				}
				if (field.help.length() > 0) {
					html.append("<p class=\"help-inline\">");
					html.append(field.help);
					html.append("</p>");
				}
				html.append("</div></div>");
				if (field.isSigned)
					html.append("<div class=\"control-group\"><div class=\"controls\"><label class=\"checkbox\"><input type=\"checkbox\" name=\"values["
							+ i
							+ "].ack\" value=\"false\"/>Kuittaa</label></div></div>");
				else
					html.append("<input type=\"hidden\" name=\"values[" + i
							+ "].ack\"/>");
				html.append("<input type=\"hidden\" name=\"values[" + i
						+ "].fieldId\" value=\"");
				html.append(field.id);
				html.append("\"/><div class=\"control-group\"><div class=\"controls\"><textarea class=\"input-xlarge\" name=\"values["
						+ i
						+ "].comment\" rows=\"3\">" + comment + "</textarea><label class=\"help-inline\">Huomautuksia</label></div></div>");
				html.append("</fieldset>");
				i++;
			}
		}
		return (html.length() > 0) ? html.toString() : null;
	}
}
