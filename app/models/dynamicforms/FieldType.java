package models.dynamicforms;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
// TODO
public enum FieldType {
	TEXT("Teksti"), INT("Kokonaisluku"), DOUBLE("Desimaaliluku"), DATE(
			"Päivämäärä"), CHECKBOX("Kyllä/Ei"), TEXTAREA("Tekstikenttä"), LEGEND(
			"Väliotsikko");

	private FieldType(final String text) {
		this.text = text;
	}

	private final String text;

	@Override
	public String toString() {
		return text;
	}

	public static Map<String, String> map() {
		Map<String, String> map = new HashMap<String, String>();
		for(FieldType value: FieldType.values())
			map.put(value.name(), value.toString());
		return map;
	}
}