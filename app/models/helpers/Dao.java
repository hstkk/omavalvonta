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
		Long count = findLongBy(query);
		if (count == null)
			return 0;
		return count;
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
		return getReference(id) != null;
	};

	@Override
	public List<T> findAll() {
		return findAll(null);
	}

	@Override
	public List<T> findAll(Integer pageNumber) {
		return findAllBy(null, pageNumber);
	}

	public List<T> findAllBy(CriteriaQuery<T> query) {
		return findAllBy(query, null);
	}

	@Override
	public List<T> findAllBy(CriteriaQuery<T> query, Integer pageNumber) {
		try {
			if (query == null) {
				CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
				query = criteriaBuilder.createQuery(clazz);
				query.from(clazz);
			}

			TypedQuery<T> q = createQuery(query);
			q = setPage(q, pageNumber);
			return q.getResultList();
		} catch (NoResultException e) {
		} catch (EntityNotFoundException e) {
		} catch (Exception e) {
			Logger.warn("findAllBy", e);
		}
		return null;
	}

	@Override
	public T findBy(CriteriaQuery<T> query) {
		try {
			TypedQuery<T> q = createQuery(query);
			return q.getSingleResult();
		} catch (NoResultException e) {
		} catch (Exception e) {
			Logger.warn("findBy", e);
		}
		return null;
	}

	@Override
	public T findById(ID id) {
		try {
			if (id != null)
				return getEm().find(clazz, id);
		} catch (NoResultException e) {
		} catch (Exception e) {
			Logger.warn("findById", e);
		}
		return null;
	}

	@Override
	public Long findLongBy(CriteriaQuery<Long> query) {
		try {
			TypedQuery<Long> q = createLongQuery(query);
			return q.getSingleResult();
		} catch (NoResultException e) {
		} catch (Exception e) {
			Logger.warn("findLongBy", e);
		}
		return null;
	}

	@Override
	public T getVersion(ID id, Date date) {
		try {
			if (id != null && date != null) {
				AuditReader auditReader = AuditReaderFactory.get(getEm());
				Number revision = auditReader.getRevisionNumberForDate(date);
				T t = auditReader.find(clazz, id, revision);
				return t;
			}
		} catch (Exception e) {
			Logger.warn("getVersion", e);
		}
		return null;
	}

	@Override
	public List<T> getVersions(ID id) {
		try {
			if (id != null) {
				List<T> versions = new ArrayList<T>();
				AuditReader auditReader = AuditReaderFactory.get(getEm());
				List<Number> revisions = auditReader.getRevisions(clazz, id);
				for (Number revision : revisions) {
					T t = auditReader.find(clazz, id, revision);
					versions.add(t);
				}
				return versions;
			}
		} catch (Exception e) {
			Logger.warn("getVersions", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> getVersionsBy(Date date, AuditCriterion auditCriterion) {
		try {
			if (date != null && auditCriterion != null) {
				AuditReader auditReader = AuditReaderFactory.get(getEm());
				Number revision = auditReader.getRevisionNumberForDate(date);
				AuditQuery query = auditReader.createQuery()
						.forEntitiesAtRevision(clazz, revision);
				query.add(auditCriterion);
				return query.getResultList();
			}
		} catch (Exception e) {
			Logger.warn("getVersionsBy", e);
		}
		return null;
	}

	@Override
	public Map<String, String> options() {
		return options(null);
	}

	@Override
	public Map<String, String> options(CriteriaQuery<T> query) {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		List<T> list = findAllBy(query);
		if (list != null)
			for (T t : list)
				map.put(t.id.toString(), t.toString());
		return map;
	};

	@Override
	public Page<T> page(int pageNumber) {
		List<T> list = null;
		long rows = count();
		if (rows > 0)
			list = findAll(pageNumber);
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