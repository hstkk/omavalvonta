package models.dynamicforms;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
//TODO
public enum FieldType {
	TEXT /*{
		public String toString() {
			return "Teksti";
		}
	}*/,
	INT /*{
		public String toString() {
			return "Kokonaisluku";
		}
	}*/,
	DOUBLE /*{
		public String toString() {
			return "Desimaaliluku";
		}
	}*/,
	DATE /*{
		public String toString() {
			return "Päivämäärä";
		}
	}*/,
	CHECKBOX /*{
		public String toString() {
			return "Kyllä ei";
		}
	}*/,
	DATETIME, TEXTAREA /*{
		public String toString() {
			return "Tekstikenttä";
		}
	}*/;

	public static Map<String, String> map() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("TEXT", "Teksti");
		map.put("INT", "Kokonaisluku");
		map.put("DOUBLE", "Desimaaliluku");
		map.put("DATE", "Päivämäärä");
		map.put("CHECKBOX", "Kyllä ei");
		map.put("TEXTAREA", "Tekstikenttä");
		return map;
	}
}