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
		map.put("Teksti", "TEXT");
		map.put("Kokonaisluku", "INT");
		map.put("Desimaaliluku", "DOUBLE");
		map.put("Kyllä ei", "CHECKBOX");
		map.put("Päivämäärä", "DATE");
		map.put("Tekstikenttä", "TEXTAREA");
		return map;
	}
}