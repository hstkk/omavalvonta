package models.helpers;

import java.io.Serializable;
import java.util.List;

import models.Term;

import play.Play;
import play.db.jpa.JPA;

public class Crud<T, ID extends Serializable> implements GeneralDao<T, ID> {

	private final Class<T> clazz;

	public Crud(Class<T> clazz) {
		this.clazz = clazz;
	}

	// TODO order by

	@Override
	public boolean create(T t) {
		try {
			JPA.em().persist(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// todo
	@Override
	public boolean createOrUpdate(T t) {
		return update(t);
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

	public T read(ID id) {
		return findById(id);
	}

	@Override
	public boolean update(T t) {
		try {
			JPA.em().merge(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(T t) {
		try {
			JPA.em().remove(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// TODO
	@Override
	public Page<T> page(int index) {
		try {
			int size = Play.application().configuration().getInt("page.size");
			if (index < 1)
				index = 1;
			Long rows = (Long) JPA.em().createQuery("select count(*) from ?")
					.setParameter(1, clazz).getSingleResult();
			List<T> list = JPA.em().createQuery("from ? order by name")
					.setParameter(1, clazz).setFirstResult((index - 1) * size)
					.setMaxResults(size).getResultList();
			if (rows != null && list != null && !list.isEmpty())
				return new Page<T>(index, size, rows, list);
		} catch (Exception e) {
		}
		return null;
	}
}