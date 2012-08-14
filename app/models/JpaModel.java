package models;

import java.util.List;

import javax.persistence.*;

import play.Play;
import play.db.jpa.*;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
@MappedSuperclass
public class JpaModel {
	@Id
	@GeneratedValue
	public Long id;

	public boolean save() {
		try {
			JPA.em().persist(this);
			return true;
		} catch (Exception e) {
			System.out.println("\n\n" + e + "\n\n");
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete() {
		try {
			JPA.em().remove(this);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update() {
		try {
			JPA.em().merge(this);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	public static class Page<T> {
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
}