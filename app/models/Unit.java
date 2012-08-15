package models;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
public enum Unit {
	KG, L;

	public static Map<String, String> map() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("L", "l");
		map.put("KG", "kg");
		return map;
	}
}