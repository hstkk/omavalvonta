package models.helpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import play.db.jpa.JPA;

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
		if(count == null)
			return 0;
		return count;
	}

	@Override
	public boolean create(T t) {
		try {
			t.onCreate();
			JPA.em().persist(t);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	};

	@Override
	public boolean delete(T t) {
		try {
			JPA.em().remove(t);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public T findById(ID id) {
		try {
			if (id != null)
				return JPA.em().find(clazz, id);
		} catch (NoResultException e) {
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public T getVersion(ID id, Date date) {
		try {
			if (id != null && date != null) {
				AuditReader auditReader = AuditReaderFactory.get(JPA.em());
				Number revision = auditReader.getRevisionNumberForDate(date);
				return auditReader.find(clazz, id, revision);
			}
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public List<T> getVersions(ID id) {
		try {
			if (id != null) {
				List<T> versions = new ArrayList<T>();
				AuditReader auditReader = AuditReaderFactory.get(JPA.em());
				List<Number> revisions = auditReader.getRevisions(clazz, id);
				for (Number revision : revisions) {
					T t = auditReader.find(clazz, id, revision);
					versions.add(t);
				}
				return versions;
			}
		} catch (Exception e) {
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
			t.onUpdate();
			JPA.em().merge(t);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}