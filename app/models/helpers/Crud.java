package models.helpers;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

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
			e.printStackTrace();
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
			Query q = JPA.em().createQuery("select e from " + entity + " e");
			q = setPage(q, pageNumber);
			return q.getResultList();
		} catch (Exception e) {
		}
		return null;
	}

	public List<T> findAllBy(String query, Object[] params) {
		return findAllBy(query, params, null);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAllBy(String query, Object[] params, Integer pageNumber) {
		try {
			Query q = createQuery(query, params);
			q = setPage(q, pageNumber);
			return q.getResultList();
		} catch (Exception e) {
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public T findBy(String query, Object[] params) {
		try {
			Query q = createQuery(query, params);
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
	public Page<T> page(int pageNumber, String order, String by) {
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