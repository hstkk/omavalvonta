package models.helpers;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {
	boolean create(T t);

	int count();

	boolean delete(T t);

	boolean exists(ID id);

	List<T> findAll();

	List<T> findAll(Integer pageNumber);

	T findById(ID id);

	Page<T> page(int pageNumber);

	boolean update(T t);
}