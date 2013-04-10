package models.helpers;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import play.db.jpa.JPA;

public class Dao<T extends Model, ID extends Serializable> extends
		JpaHelper<T, ID> implements GenericDao<T, ID> {

	private final String entity;

	public Dao(Class<T> clazz) {
		super(clazz);
		this.entity = clazz.getName();
	}

	@Override
	public long count() {
		try {
			return (long) JPA.em()
					.createQuery("select count(*) from " + entity + " e")
					.getSingleResult();
		} catch (NoResultException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean create(T t) {
		try {
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

	// TODO test
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
		} catch (Exception e) {
			e.printStackTrace();
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
			JPA.em().merge(t);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}