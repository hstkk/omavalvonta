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
	AINA(0, "joka erä"), JOSKUS(1, "kerran lukukaudessa");

	private When(int ordinal, String name) {
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

	public static When setValue(int ordinal) {
		When when = null;
		for (When value : When.values())
			if (value.getValue() == ordinal) {
				when = value;
				break;
			}
		return when;
	}

	private final int ordinal;
	private final String name;

	public static Map<String, String> map() {
		Map<String, String> map = new HashMap<String, String>();
		for (When value : When.values())
			map.put(Integer.toString(value.getValue()), value.toString());
		return map;
	}
}