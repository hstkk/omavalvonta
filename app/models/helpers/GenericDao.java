package models.helpers;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaQuery;

public interface GenericDao<T, ID extends Serializable> {
	boolean create(T t);

	long count();

	boolean delete(T t);

	boolean exists(ID id);

	List<T> findAll();

	List<T> findAll(Integer pageNumber);

	List<T> findAllBy(String query, Object[] params);

	List<T> findAllBy(String query, Object[] params, Integer pageNumber);

	T findBy(CriteriaQuery<T> query);

	T findById(ID id);

	Map<String, String> options();

	Page<T> page(int pageNumber, String order, String by);

	boolean update(T t);
}