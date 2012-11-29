package models.helpers;

import java.io.Serializable;

public interface GeneralDao<T, ID extends Serializable> {
	boolean create(T t);

	T read(ID id);

	boolean update(T t);

	boolean createOrUpdate(T t);

	boolean delete(T t);

	Page<T> page(int index);
}