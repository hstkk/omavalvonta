package models.helpers;

import java.io.Serializable;

public interface GenericDao<T, ID extends Serializable> {
	boolean create(T t);

	T findById(ID id);

	boolean update(T t);

	boolean delete(T t);

	Page<T> page(int index);
}