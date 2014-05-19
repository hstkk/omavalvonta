package models.helpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import play.Logger;
import play.Play;
import play.db.jpa.JPA;
import com.google.common.base.Optional;

public class JpaHelper<T extends Model, ID extends Serializable> {
	protected final Class<T> clazz;
	protected final int pageSize;

	public JpaHelper(Class<T> clazz) {
		this.clazz = clazz;
		this.pageSize = Play.application().configuration().getInt("page.size");
	}

	protected TypedQuery<Long> createLongQuery(CriteriaQuery<Long> query) {
		return getEm().createQuery(query);
	}

	protected TypedQuery<T> createQuery(CriteriaQuery<T> query) {
		return getEm().createQuery(query);
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return getEm().getCriteriaBuilder();
	}

	public EntityManager getEm() {
		return JPA.em();
	}

	public Optional<T> getReference(ID id) {
		try {
			Optional<ID> optId = Optional.fromNullable(id);
			if (optId.isPresent())
				return Optional.fromNullable(getEm().getReference(clazz, optId.get()));
		} catch (EntityNotFoundException e) {
		} catch (Exception e) {
			Logger.warn("getReference", e);
		}
		return Optional.absent();
	}

	public List<T> getReference(List<T> list) {
		ArrayList<T> references = new ArrayList<T>();
		Optional<List<T>> optList = Optional.fromNullable(list);
		try {
			if (optList.isPresent())
				for (T t : optList.get()) {
					Optional<T> reference = getReference(t);
					if (reference.isPresent())
						references.add(reference.get());
				}
		} catch (Exception e) {
			Logger.warn("getReference", e);
		}
		return references;
	}

	@SuppressWarnings("unchecked")
	public Optional<T> getReference(T t) {
		Optional<T> optT = Optional.fromNullable(t);
		if (optT.isPresent())
			return getReference((ID) optT.get().id);
		return Optional.absent();
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
