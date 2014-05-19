package models.helpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.envers.query.criteria.AuditCriterion;
import play.Logger;
import com.google.common.base.Optional;

public class Dao<T extends Model, ID extends Serializable> extends
		JpaHelper<T, ID> implements GenericDao<T, ID> {

	public Dao(Class<T> clazz) {
		super(clazz);
	}

	@Override
	public long count() {
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		Root<T> root = query.from(clazz);
		query.select(criteriaBuilder.count(root));
		Optional<Long> count = findLongBy(query);
		if (count.isPresent())
			return count.get();
		return 0;
	}

	@Override
	public boolean create(T t) {
		try {
			boolean success = t.onCreate();
			if (success) {
				getEm().persist(t);
				return true;
			}
		} catch (Exception e) {
			Logger.warn("create", e);
		}
		return false;
	};

	@Override
	public boolean delete(T t) {
		try {
			getEm().remove(t);
			return true;
		} catch (Exception e) {
			Logger.warn("delete", e);
		}
		return false;
	}

	@Override
	public boolean doesNotExist(ID id) {
		return !exists(id);
	}

	@Override
	public boolean exists(ID id) {
		return Optional.fromNullable(getReference(id)).isPresent();
	};
	
	@Override
	public Optional<List<T>> findAll() {
		return findAll(Optional.<Integer>absent());
	}

	@Override
	public Optional<List<T>> findAll(Integer pageNumber) {
		return findAll(Optional.fromNullable(pageNumber));
	}

	@Override
	public Optional<List<T>> findAll(Optional<Integer> pageNumber) {
		return findAllBy(Optional.<CriteriaQuery<T>>absent(), pageNumber);
	}

	@Override
	public Optional<List<T>> findAllBy(CriteriaQuery<T> query) {
		return findAllBy(Optional.fromNullable(query), Optional.<Integer>absent());
	}

	@Override
	public Optional<List<T>> findAllBy(Optional<CriteriaQuery<T>> query) {
		return findAllBy(query, Optional.<Integer>absent());
	}

	@Override
	public Optional<List<T>> findAllBy(Optional<CriteriaQuery<T>> query, Optional<Integer> pageNumber) {
		try {
			if (!query.isPresent()) {
				CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
				query = Optional.fromNullable(criteriaBuilder.createQuery(clazz));
				query.get().from(clazz);
			}

			TypedQuery<T> q = createQuery(query.get());
			q = setPage(q, pageNumber);
			return Optional.fromNullable(q.getResultList());
		} catch (NoResultException e) {
		} catch (EntityNotFoundException e) {
		} catch (Exception e) {
			Logger.warn("findAllBy", e);
		}
		return Optional.absent();
	}

	@Override
	public Optional<T> findBy(CriteriaQuery<T> query) {
		try {
			TypedQuery<T> q = createQuery(query);
			return Optional.fromNullable(q.getSingleResult());
		} catch (NoResultException e) {
		} catch (Exception e) {
			Logger.warn("findBy", e);
		}
		return Optional.absent();
	}

	@Override
	public Optional<T> findById(ID id) {
		return findById(Optional.fromNullable(id));
	}

	@Override
	public Optional<T> findById(Optional<ID> id) {
		try {
			if (id.isPresent())
				return Optional.fromNullable(getEm().find(clazz, id.get()));
		} catch (NoResultException e) {
		} catch (Exception e) {
			Logger.warn("findById", e);
		}
		return Optional.absent();
	}

	@Override
	public Optional<Long> findLongBy(CriteriaQuery<Long> query) {
		try {
			TypedQuery<Long> q = createLongQuery(query);
			return Optional.fromNullable(q.getSingleResult());
		} catch (NoResultException e) {
		} catch (Exception e) {
			Logger.warn("findLongBy", e);
		}
		return Optional.absent();
	}

	@Override
	public Optional<T> getVersion(ID id, Date date) {
			return getVersion(Optional.fromNullable(id), Optional.fromNullable(date));
	}

	@Override
	public Optional<T> getVersion(Optional<ID> id, Optional<Date> date) {
		try {
			if (id.isPresent() && date.isPresent()) {
				AuditReader auditReader = AuditReaderFactory.get(getEm());
				Number revision = auditReader.getRevisionNumberForDate(date.get());
				T t = auditReader.find(clazz, id.get(), revision);
				return Optional.fromNullable(t);
			}
		} catch (Exception e) {
			Logger.warn("getVersion", e);
		}
		return Optional.absent();
	}

	@Override
	public Optional<List<T>> getVersions(ID id) {
		return getVersions(Optional.fromNullable(id));
	}

	@Override
	public Optional<List<T>> getVersions(Optional<ID> id) {
		try {
			if (id.isPresent()) {
				List<T> versions = new ArrayList<T>();
				AuditReader auditReader = AuditReaderFactory.get(getEm());
				List<Number> revisions = auditReader.getRevisions(clazz, id.get());
				for (Number revision : revisions) {
					T t = auditReader.find(clazz, id.get(), revision);
					versions.add(t);
				}
				return Optional.fromNullable(versions);
			}
		} catch (Exception e) {
			Logger.warn("getVersions", e);
		}
		return Optional.absent();
	}

	@Override
	public Optional<List<T>> getVersionsBy(Date date, AuditCriterion auditCriterion) {
		return getVersionsBy(Optional.fromNullable(date), Optional.fromNullable(auditCriterion));
	}

	@Override
	@SuppressWarnings("unchecked")
	public Optional<List<T>> getVersionsBy(Optional<Date> date, Optional<AuditCriterion> auditCriterion) {
		try {
			if (date.isPresent()&& auditCriterion.isPresent()) {
				AuditReader auditReader = AuditReaderFactory.get(getEm());
				Number revision = auditReader.getRevisionNumberForDate(date.get());
				AuditQuery query = auditReader.createQuery()
						.forEntitiesAtRevision(clazz, revision);
				query.add(auditCriterion.get());
				return Optional.fromNullable((List<T>) query.getResultList());
			}
		} catch (Exception e) {
			Logger.warn("getVersionsBy", e);
		}
		return Optional.absent();
	}

	@Override
	public Map<String, String> options() {
		return options(Optional.<CriteriaQuery<T>>absent());
	}

	@Override
	public Map<String, String> options(Optional<CriteriaQuery<T>> query) {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		Optional<List<T>> list = Optional.fromNullable((List<T>) findAllBy(query));
		if (list.isPresent())
			for (T t : list.get())
				map.put(t.id.toString(), t.toString());
		return map;
	};

	@Override
	public Page<T> page(int pageNumber) {
		List<T> list = new ArrayList<T>();
		long rows = count();
		if (rows > 0)
			list = findAll(pageNumber).get();
		return new Page<T>(pageNumber, pageSize, rows, list);
	};

	@Override
	public boolean update(T t) {
		try {
			boolean success = t.onUpdate();
			if (success) {
				getEm().merge(t);
				return true;
			}
		} catch (Exception e) {
			Logger.warn("update", e);
		}
		return false;
	}
}