package utils;

import java.util.*;

import models.Term;
import models.TermCategory;
import models.dynamicforms.Field;
import models.dynamicforms.FieldType;
import models.dynamicforms.Result;
import forms.dynamicforms.Fieldset;

public class Form {

	static List<Term> terms;
	
	// TODO label for
	public static <T> String formify(List<T> list) {
		if (list == null || list.isEmpty())
			return null;

		int i = 0;

		terms = Term.findByCategory(TermCategory.REASON);

		StringBuilder html = new StringBuilder();
		for (Object e : list) {
			Field field = null;
			String value = "", comment = "";
			Long id = null;
			if (Field.class.isAssignableFrom(e.getClass()))
				field = (Field) e;
			else if (Result.class.isAssignableFrom(e.getClass())) {
				Result result = (Result) e;
				field = Field.findById(result.field.id);
				value = result.toString();
				comment = result.comment;
				id = result.id;
			} else if (Fieldset.class.isAssignableFrom(e.getClass())) {
				Fieldset fieldset = (Fieldset) e;
				field = Field.findById(fieldset.fieldId);
				value = fieldset.value;
				comment = fieldset.comment;
			}

			if (field != null) {
				if (field.fieldTypeEnum == FieldType.LEGEND) {
					html.append("<fieldset><legend>");
					html.append(field.name);
					html.append("</fieldset></legend>");
				} else {
					if (id != null) {
						html.append("<input type=\"hidden\" name=\"values[");
						html.append(i);
						html.append("].id\" value=\"");
						html.append(id);
						html.append("\"/>");
					}
					html.append("<fieldset><div class=\"control-group\"><label class=\"control-label\">");
					html.append(field.name);
					html.append("</label><div class=\"controls\">");
					switch (field.fieldTypeEnum) {
					case CHECKBOX:
						boolean bool = false;
						try {
							bool = Boolean.parseBoolean(value);
						} catch (Exception ex) {
						}
						html.append("<label class=\"radio inline\">");
						html.append("<input type=\"radio\" value=\"true\" name=\"values[");
						html.append(i);
						html.append("].value\"");
						if (bool)
							html.append(" checked");
						html.append(">Ok</label>");
						html.append("<label class=\"radio inline\">");
						html.append("<input type=\"radio\" value=\"false\" name=\"values[");
						html.append(i);
						html.append("].value\"");
						if (!bool)
							html.append(" checked");
						html.append(">Ei ok</label><br/>");
						break;
					case DATE:
						html.append("<input class=\"input-xlarge\" name=\"values["
								+ i
								+ "].value\" type=\"text\" pattern=\"\\d{1,2}\\.\\d{1,2}\\.\\d{4}\"  placeholder=\"pp.kk.vvvv\" value=\"");
						html.append(value);
						html.append("\"/>");
						break;
					case INT:
						html.append("<input class=\"input-xlarge\" name=\"values["
								+ i
								+ "].value\" type=\"number\" placeholder=\"0\" value=\"");
						html.append(value);
						html.append("\"/>");
						break;
					case DOUBLE:
						html.append("<input class=\"input-xlarge\" name=\"values["
								+ i
								+ "].value\" type=\"number\" placeholder=\"0,0\" value=\"");
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
					if (field.help.length() > 0
							|| (field.when != null && !field.when.toString()
									.equals(""))) {
						html.append("<p class=\"help-inline\">");
						if (field.help.length() > 0)
							html.append(field.help);
						if (field.when != null
								&& !field.when.toString().equals("")) {
							html.append(" Määritystiheys ");
							html.append(field.when.toString());
						}
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
							+ "].comment\" rows=\"3\">"
							+ comment
							+ "</textarea><label class=\"help-inline\">Huomautuksia</label></div></div>");

					if (value != null || !value.equals(""))
						html.append(optionifyReason(i));

					html.append("</fieldset>");
					i++;
				}
			}
		}
		return (html.length() > 0) ? html.toString() : null;
	}

	private static String optionifyReason(int i){
		StringBuilder html = new StringBuilder();
	    html.append("<div class=\"control-group\"><label class=\"control-label\">Muutoksen syy</label><select id=\"reasonId\" name=\"values["
	    		+ i
	    		+ "].reasonId\" rows=\"3\">");
	    html.append("<option class=\"blank\" value=\"\"></option>");
	    for(Term term: terms){
	    	 html.append("<option value=\"");
	    	 html.append(term.id);
	    	 html.append("\" >");
	    	 html.append(term.name);
	    	 html.append("</option>");
	    }
	    html.append("</select></div>");
		return html.toString();
	}
}
