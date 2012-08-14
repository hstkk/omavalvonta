package models;

import java.util.List;
import play.Play;

public class Page<T> {
	private final int size;
	private final int index;
	private final List<T> list;

	public Page(List<T> list, int index) {
		this.size = Play.application().configuration().getInt("page.size");
		this.list = list;
		this.index = index;
	}

	public boolean hasPrevoius() {
		return index > 1;
	}

	public boolean hasNext() {
		return (list.size() / size) >= index;
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