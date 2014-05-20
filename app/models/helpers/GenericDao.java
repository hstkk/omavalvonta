package models.helpers;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaQuery;

public interface GenericDao<T, ID extends Serializable> {
	long count();

	boolean create(T t);

	boolean delete(T t);

	boolean doesNotExist(ID id);

	boolean exists(ID id);

	List<T> findAll();

	List<T> findAll(Integer pageNumber);

	List<T> findAllBy(CriteriaQuery<T> query);

	List<T> findAllBy(CriteriaQuery<T> query, Integer pageNumber);

	T findBy(CriteriaQuery<T> query);

	T findById(ID id);

	Long findLongBy(CriteriaQuery<Long> query);

	T getVersion(ID id, Date date);

	List<T> getVersions(ID id);

	Map<String, String> options();

	Map<String, String> options(CriteriaQuery<T> query);

	Page<T> page(int pageNumber);

	boolean update(T t);
}