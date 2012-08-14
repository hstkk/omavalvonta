package models.helper;

import java.util.List;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
public class Page<T> {
	private final int size;
	private final int index;
	private final int rows;
	private final List<T> list;

	public Page(int index, int size, int rows, List<T> list) {
		this.index = index;
		this.size = size;
		this.rows = rows;
		this.list = list;
	}

	public boolean hasPrevoius() {
		return index > 1;
	}

	public boolean hasNext() {
		return (rows / size) >= index;
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public int getIndex() {
		return index;
	}

	public List<T> getList() {
		return list;
	}
}