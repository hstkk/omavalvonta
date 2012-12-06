package models.helpers;

import java.io.Serializable;

public interface GenericDao<T, ID extends Serializable> {
	boolean exists(T t);

	boolean create(T t);

	int count();

	//List<T> findAll();

	//List<T> findAllByProperty(String property, Object value);

	T findById(ID id);

	boolean update(T t);

	boolean delete(T t);

	Page<T> page(int index);
}