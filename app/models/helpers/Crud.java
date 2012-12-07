package models.helpers;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import play.Play;
import play.db.jpa.JPA;

public class Crud<T extends Model, ID extends Serializable> implements
		GenericDao<T, ID> {

	private final Class<T> clazz;
	private final String entity;
	private final int pageSize;

	public Crud(Class<T> clazz) {
		this.clazz = clazz;
		this.entity = clazz.getName();
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
			return (int) JPA.em()
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
				return (boolean) JPA
						.em()
						.createQuery(
								"select count(*) from " + entity + " e"
										+ " where e.id = ?")
						.setParameter(1, id).getSingleResult();
		} catch (Exception e) {
		}
		return false;
	};

	@Override
	public List<T> findAll() {
		return findAll(null);
	}

	@Override
	public List<T> findAll(Integer pageNumber) {
		List<T> list = null;
		try {
			Query query = JPA.em().createQuery("select e from " + entity + " e");
			if (pageNumber != null) {
				if (pageNumber < 1)
					pageNumber = 1;
				query.setFirstResult((pageNumber - 1) * pageSize)
						.setMaxResults(pageSize);
			}
			list = query.getResultList();
		} catch (Exception e) {
		}
		return list;
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