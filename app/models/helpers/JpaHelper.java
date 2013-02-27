package models.helpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import play.Play;
import play.db.jpa.JPA;

public class JpaHelper<T extends Model, ID extends Serializable> {
	protected final Class<T> clazz;
	protected final int pageSize;

	public JpaHelper(Class<T> clazz) {
		this.clazz = clazz;
		this.pageSize = Play.application().configuration().getInt("page.size");
	}

	protected Query createQuery(CriteriaQuery<T> query) {
		return JPA.em().createQuery(query);
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return JPA.em().getCriteriaBuilder();
	}

	public T getReference(ID id) {
		try {
			return JPA.em().getReference(clazz, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> getReference(List<T> list) {
		ArrayList<T> references = new ArrayList<T>();
		try {
			for (T t : list)
				if (t.id != null)
					references.add(getReference((ID) t.id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return references;
	}

	protected Query setPage(Query q, Integer pageNumber) {
		if (pageNumber != null) {
			if (pageNumber < 1)
				pageNumber = 1;
			q.setFirstResult((pageNumber - 1) * pageSize).setMaxResults(
					pageSize);
		}
		return q;
	}
}
