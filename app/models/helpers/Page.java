package models.helpers;

import java.util.ArrayList;
import java.util.List;
import com.google.common.base.Optional;

/**
 * The Class Page.
 * 
 * @param <T>
 *            the generic type
 * @author Sami Hostikka <dev@01.fi>
 */
public class Page<T> {

	/** The size. */
	private final int pageSize;

	/** The index. */
	private final int pageNumber;

	/** The rows. */
	private final long rows;

	/** The list. */
	private final Optional<List<T>> list;

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
	public Page(int pageNumber, int pageSize, long rows, List<T> list) {
		this.pageNumber = (pageNumber < 1) ? 1 : pageNumber;
		this.pageSize = pageSize;
		this.rows = rows;
		this.list = Optional.fromNullable(list);
	}

	/**
	 * Checks for previous.
	 * 
	 * @return true, if successful
	 */
	public boolean hasPrevious() {
		return pageNumber > 1;
	}

	/**
	 * Checks for next.
	 * 
	 * @return true, if successful
	 */
	public boolean hasNext() {
		return (rows / pageSize) >= pageNumber;
	}

	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return (!list.isPresent() || list.get().isEmpty());
	}

	/**
	 * Gets the index.
	 * 
	 * @return the index
	 */
	public int getIndex() {
		return pageNumber;
	}

	public int getPrevious() {
		return pageNumber - 1;
	}

	public int getNext() {
		return pageNumber + 1;
	}

	/**
	 * Gets the list.
	 * 
	 * @return the list
	 */
	public List<T> getList() {
		return list.or(new ArrayList<T>());
	}
}