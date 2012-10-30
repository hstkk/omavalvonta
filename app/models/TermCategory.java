package models;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
// TODO documentation
public enum TermCategory {
	FORMTYPE(0), REASON(1), UNIT(2);

	private TermCategory(int ordinal) {
		this.ordinal = ordinal;
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
}