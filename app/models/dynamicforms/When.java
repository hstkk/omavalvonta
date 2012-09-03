package models.dynamicforms;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
// TODO
public enum When {
	AINA, JOSKUS;

	public static Map<String, String> map() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("", "");
		map.put("AINA", "Joka erä");
		map.put("JOSKUS", "Kerran lukukaudessa");
		return map;
	}
}