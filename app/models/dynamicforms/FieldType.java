package models.dynamicforms;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
public enum FieldType {
	TEXT, INT, DOUBLE, DATE, CHECKBOX, DATETIME, TEXTAREA;
	
	public static Map<String, String> map(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("TEXT", "Teksti");
		map.put("INT", "Kokonaisluku");
		map.put("DOUBLE", "Desimaaliluku");
		map.put("DATE", "Kyllä ei");
		map.put("CHECKBOX", "Päivämäärä");
		map.put("TEXTAREA", "Tekstikenttä");
		return map;
	}
}