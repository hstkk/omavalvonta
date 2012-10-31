package models.helpers;

import java.io.Serializable;

import play.db.jpa.JPA;

public class Crud<T, ID extends Serializable> implements GeneralDao<T, ID> {

	private final Class<T> clazz;

	public Crud(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public boolean create(T t) {
		try {
			JPA.em().persist(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public T read(ID id) {
		try {
			if (id != null)
				return JPA.em().find(clazz, id);
		} catch (Exception e) {
		}
		return null;
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

	@Override
	public Page<T> page(int index) {
		// TODO Auto-generated method stub
		return null;
	}
}