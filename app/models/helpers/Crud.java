package models.helpers;

import java.io.Serializable;
import java.util.List;

import play.Play;
import play.db.jpa.JPA;

public class Crud<T, ID extends Serializable> implements GenericDao<T, ID> {

	private final Class<T> clazz;
	private final String table;
	private final int pageSize;

	public Crud(Class<T> clazz) {
		this.clazz = clazz;
		this.table = clazz.getName();
		this.pageSize = Play.application().configuration().getInt("page.size");
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
	public int count() {
		try {
			return (int) JPA.em().createQuery("select count(*) from " + table)
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
			return (boolean) JPA
					.em()
					.createQuery(
							"select count(*) from " + table + " where id = ?")
					.setParameter(1, id).getSingleResult();
		} catch (Exception e) {
		}
		return false;
	};

	@Override
	public List<T> findAll() {
		try {
			List<T> list = JPA.em().createQuery("from " + table)
					.getResultList();
			return list;
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public List<T> findAll(int pageNumber) {
		try {
			if (pageNumber < 1)
				pageNumber = 1;
			List<T> list = JPA.em().createQuery("from " + table)
					.setFirstResult((pageNumber - 1) * pageSize)
					.setMaxResults(pageSize).getResultList();
			return list;
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
		int rows = count();
		try {
			if (rows > 0)
				list = findAll(pageNumber);
		} catch (Exception e) {
		}
		return new Page<T>(pageNumber, pageSize, rows, list);
	}

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