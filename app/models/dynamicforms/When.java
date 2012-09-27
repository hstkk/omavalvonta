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
	AINA("joka erä"), JOSKUS("kerran lukukaudessa");

	private When(final String text) {
		this.text = text;
	}

	private final String text;

	@Override
	public String toString() {
		return text;
	}

	public static Map<String, String> map() {
		Map<String, String> map = new HashMap<String, String>();
		for(When value: When.values())
			map.put(value.name(), value.toString());
		return map;
	}
}