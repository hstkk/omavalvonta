package models;

import java.util.HashMap;
import java.util.Map;

import models.dynamicforms.FieldType;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
// TODO localization
public enum TermCategory {
	FORMTYPE(0, "Lomaketyyppi"), REASON(1, "Muutoksen syy"), UNIT(2,
			"Mittayksikkö"), DESTINY(3, "Erän Kohtalo myyntihyväksynnässä");

	private TermCategory(int ordinal, String name) {
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

	public static TermCategory setValue(int ordinal) {
		TermCategory category = null;
		for (TermCategory value : TermCategory.values())
			if (value.getValue() == ordinal) {
				category = value;
				break;
			}
		return category;
	}

	private final int ordinal;
	private final String name;

	public static Map<String, String> options() {
		Map<String, String> map = new HashMap<String, String>();
		for (TermCategory value : TermCategory.values())
			map.put(Integer.toString(value.getValue()), value.toString());
		return map;
	}
}