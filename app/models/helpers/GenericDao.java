package models.helpers;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.envers.query.criteria.AuditCriterion;
import com.google.common.base.Optional;


public interface GenericDao<T, ID extends Serializable> {
	long count();

	boolean create(T t);

	boolean delete(T t);

	boolean doesNotExist(ID id);

	boolean exists(ID id);

	Optional<List<T>> findAll();

	Optional<List<T>> findAll(Integer pageNumber);

	Optional<List<T>> findAll(Optional<Integer> pageNumber);

	Optional<List<T>> findAllBy(CriteriaQuery<T> query);

	Optional<List<T>> findAllBy(Optional<CriteriaQuery<T>> query);

	Optional<List<T>> findAllBy(Optional<CriteriaQuery<T>> query, Optional<Integer> pageNumber);

	Optional<T> findBy(CriteriaQuery<T> query);

	Optional<T> findById(ID id);

	Optional<T> findById(Optional<ID> id);

	Optional<Long> findLongBy(CriteriaQuery<Long> query);

	Optional<T> getVersion(ID id, Date date);

	Optional<T> getVersion(Optional<ID> id, Optional<Date> date);

	Optional<List<T>> getVersions(ID id);

	Optional<List<T>> getVersions(Optional<ID> id);

	Optional<List<T>> getVersionsBy(Date date, AuditCriterion auditCriterion);

	Optional<List<T>> getVersionsBy(Optional<Date> date, Optional<AuditCriterion> auditCriterion);

	Map<String, String> options();

	Map<String, String> options(Optional<CriteriaQuery<T>> query);

	Page<T> page(int pageNumber);

	boolean update(T t);
}