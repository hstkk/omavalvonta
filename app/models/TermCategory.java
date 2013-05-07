package models;

import java.util.HashMap;
import java.util.Map;

import utils.Helper;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
public enum TermCategory {
	REASON(0, Helper.getMessage("enum.REASON")), UNIT(1, Helper
			.getMessage("enum.UNIT")), DESTINY(2, Helper
			.getMessage("enum.DESTINY"));

	private final int ordinal;
	private final String name;

	private TermCategory(int ordinal, String name) {
		this.ordinal = ordinal;
		this.name = name;
	}

	public static Map<String, String> options() {
		Map<String, String> map = new HashMap<String, String>();
		for (TermCategory value : TermCategory.values())
			map.put(Integer.toString(value.getValue()), value.toString());
		return map;
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

	public int getValue() {
		return ordinal;
	}

	@Override
	public String toString() {
		return name;
	}
}