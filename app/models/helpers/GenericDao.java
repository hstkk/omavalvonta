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

	//List<T> findAllByProperty(String property, Object value);

	T findById(ID id);

	//T findByProperty(String property, Object value);

	Page<T> page(int pageNumber);

	boolean update(T t);
}