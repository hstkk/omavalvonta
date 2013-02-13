package models.dynamicforms;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
public enum FormType {
	INGREDIENT(0, "Raaka-aineen vastaanottotiedot"), INT(1, "Puhtaustarkkailu"), DOUBLE(2, "Pesuohjelma"), DATE(
			3, "Tuotekortti");

	private FormType(int ordinal, String name) {
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

	public static FormType setValue(int ordinal) {
		FormType type = null;
		for (FormType value : FormType.values())
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
		for (FormType value : FormType.values())
			map.put(Integer.toString(value.getValue()), value.toString());
		return map;
	}
}