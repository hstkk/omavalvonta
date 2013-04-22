package models.helpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
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

	protected TypedQuery<T> createQuery(CriteriaQuery<T> query) {
		return JPA.em().createQuery(query);
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return JPA.em().getCriteriaBuilder();
	}

	public T getReference(ID id) {
		try {
			if (id != null)
				return JPA.em().getReference(clazz, id);
		} catch (EntityNotFoundException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<T> getReference(List<T> list) {
		ArrayList<T> references = new ArrayList<T>();
		try {
			for (T t : list)
				references.add(getReference(t));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return references;
	}

	public T getReference(T t) {
		try {
			if (t.id != null)
				return JPA.em().getReference(clazz, t.id);
		} catch (EntityNotFoundException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<T> getReferences(List<ID> list) {
		ArrayList<T> references = new ArrayList<T>();
		try {
			for (ID id : list)
				references.add(getReference(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return references;
	}

	protected TypedQuery<T> setPage(TypedQuery<T> q, Integer pageNumber) {
		if (pageNumber != null) {
			if (pageNumber < 1)
				pageNumber = 1;
			q.setFirstResult((pageNumber - 1) * pageSize).setMaxResults(
					pageSize);
		}
		return q;
	}
}
