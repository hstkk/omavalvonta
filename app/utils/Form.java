package utils;

import java.util.*;
import models.dynamicforms.Field;

public class Form {

	// TODO label for
	public static String formify(List<Field> fields) {
		if (fields == null || fields.isEmpty())
			return null;
		StringBuilder html = new StringBuilder();
		for (Field field : fields) {
			html.append("<fieldset><div class=\"control-group\"><label class=\"control-label\">");
			html.append(field.name);
			html.append("</label><div class=\"controls\">");
			switch (field.type) {
			case CHECKBOX:
				html.append("<input type=\"checkbox\" name=\"values[].value\"/>");
				break;
			case DATE:
				html.append("<input class=\"input-xlarge\" name=\"values[].value\" type=\"text\" placeholder=\"pp.kk.vvvv\"/>");
				break;
			case INT:
				html.append("<input class=\"input-xlarge\" name=\"values[].value\" type=\"text\" placeholder=\"0\"/>");
				break;
			case DOUBLE:
				html.append("<input class=\"input-xlarge\" name=\"values[].value\" type=\"text\" placeholder=\"0,0\"/>");
				break;
			case TEXT:
				html.append("<input class=\"input-xlarge\" name=\"values[].value\" type=\"text\"/>");
				break;
			case TEXTAREA:
				html.append("<textarea class=\"input-xlarge\" name=\"values[].value\" rows=\"3\"></textarea>");
				break;
			}
			if (field.help.length() > 0) {
				html.append("<p class=\"help-block\">");
				html.append(field.help);
				html.append("</p>");
			}
			html.append("</div></div>");
			if (field.isSigned)
				html.append("<div class=\"control-group\"><div class=\"controls\"><label class=\"checkbox\"><input type=\"checkbox\" name=\"values[].ack\"/>Kuittaa</label></div></div>");
			else
				html.append("<input type=\"hidden\" name=\"values[].ack\"/>");
			html.append("<div class=\"control-group\"><div class=\"controls\"><textarea class=\"input-xlarge\" name=\"values[].comment\" rows=\"3\"></textarea><label class=\"help-inline\">Huomautuksia</label></div></div>");
			html.append("</fieldset>");
		}
		return (html.length() > 0) ? html.toString() : null;
	}
}
