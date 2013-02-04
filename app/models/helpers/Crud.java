package models.helpers;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import models.User;

import play.db.jpa.JPA;

public class Crud<T extends Model, ID extends Serializable> extends JpaHelper
		implements GenericDao<T, ID> {

	private final Class<T> clazz;
	private final String entity;

	public Crud(Class<T> clazz) {
		this.clazz = clazz;
		this.entity = clazz.getName();
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
	}

	@Override
	public long count() {
		try {
			return (long) JPA.em()
					.createQuery("select count(*) from " + entity + " e")
					.getSingleResult();
		} catch (Exception e) {
		}
		return 0;
	};

	@Override
	public boolean delete(T t) {
		try {
			JPA.em().remove(t);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean exists(ID id) {
		try {
			if (id != null)
				return ((long) JPA
						.em()
						.createQuery(
								"select count(*) from " + entity + " e"
										+ " where e.id = ?")
						.setParameter(1, id).getSingleResult()) == 1;
		} catch (Exception e) {
		}
		return false;
	};

	@Override
	public List<T> findAll() {
		return findAll(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Integer pageNumber) {
		try {
			CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
			CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
System.out.println(query.toString());
			Query q = createQuery(query);
			q = setPage(q, pageNumber);
			return q.getResultList();
		} catch (Exception e) {
		}
		return null;
	}

	public List<T> findAllBy(CriteriaQuery<T> query) {
		return findAllBy(query, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllBy(CriteriaQuery<T> query, Integer pageNumber) {
		try {
			Query q = createQuery(query);
			q = setPage(q, pageNumber);
			return q.getResultList();
		} catch (Exception e) {
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findBy(CriteriaQuery<T> query) {
		try {
			Query q = createQuery(query);
			return (T) q.getSingleResult();
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public T findById(ID id) {
		try {
			if (id != null)
				return JPA.em().find(clazz, id);
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public Page<T> page(int pageNumber) {
		List<T> list = null;
		long rows = count();
		try {
			if (rows > 0)
				list = findAll(pageNumber);
		} catch (Exception e) {
		}
		return new Page<T>(pageNumber, pageSize, rows, list);
	}

	@Override
	public java.util.Map<String, String> options() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		List<T> list = findAll();
		for (T t : list)
			map.put(t.id.toString(), t.toString());
		return map;
	};

	@Override
	public boolean update(T t) {
		try {
			JPA.em().merge(t);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}