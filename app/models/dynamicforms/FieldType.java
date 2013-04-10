package models.dynamicforms;

import java.util.HashMap;
import java.util.Map;

import utils.Helper;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
public enum FieldType {
	TEXT(0, Helper.getMessage("enum.TEXT")), INT(1, Helper
			.getMessage("enum.INT")), DOUBLE(2, Helper
			.getMessage("enum.DOUBLE")), DATE(3, Helper.getMessage("enum.DATE")), CHECKBOX(
			4, Helper.getMessage("enum.CHECKBOX")), TEXTAREA(5, Helper
			.getMessage("enum.TEXTAREA"));

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