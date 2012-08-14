package models.helpers;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Page.
 * 
 * @param <T>
 *            the generic type
 * @author Sami Hostikka <dev@01.fi>
 */
public class Page<T> {

	/** The size. */
	private final int size;

	/** The index. */
	private final int index;

	/** The rows. */
	private final int rows;

	/** The list. */
	private final List<T> list;

	/**
	 * Instantiates a new page.
	 * 
	 * @param index
	 *            the index
	 * @param size
	 *            the size
	 * @param rows
	 *            the rows
	 * @param list
	 *            the list
	 */
	public Page(int index, int size, int rows, List<T> list) {
		this.index = index;
		this.size = size;
		this.rows = rows;
		this.list = list;
	}

	/**
	 * Checks for previous.
	 * 
	 * @return true, if successful
	 */
	public boolean hasPrevious() {
		return index > 1;
	}

	/**
	 * Checks for next.
	 * 
	 * @return true, if successful
	 */
	public boolean hasNext() {
		return (rows / size) >= index;
	}

	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Gets the index.
	 * 
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Gets the list.
	 * 
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}
}