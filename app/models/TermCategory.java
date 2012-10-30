package models;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
// TODO documentation
public enum TermCategory {
	FORMTYPE(0), REASON(1), UNIT(2);

	private TermCategory(final int id) {
		this.id = id;
	}

	private final int id;
}