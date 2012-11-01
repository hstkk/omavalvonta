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
	TEXT(0, "Teksti"), INT(1, "Kokonaisluku"), DOUBLE(2, "Desimaaliluku"), DATE(
			3, "Päivämäärä"), CHECKBOX(4, "Kyllä/Ei"), TEXTAREA(5,
			"Tekstikenttä"), LEGEND(6, "Väliotsikko");

	private FieldType(int ordinal, String name) {
		this.ordinal = ordinal;
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public int getValue() {
		return ordinal;
	}

	public static FieldType setValue(int ordinal) {
		FieldType type = null;
		for (FieldType value : FieldType.values())
			if (value.getValue() == ordinal) {
				type = value;
				break;
			}
		return type;
	}

	private final int ordinal;
	private final String name;

	public static Map<String, String> map() {
		Map<String, String> map = new HashMap<String, String>();
		for (FieldType value : FieldType.values())
			map.put(Integer.toString(value.getValue()), value.toString());
		return map;
	}
}