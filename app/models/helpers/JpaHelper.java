package models.helpers;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import play.Play;
import play.db.jpa.JPA;

public class JpaHelper {
	protected final int pageSize;

	public JpaHelper() {
		this.pageSize = Play.application().configuration().getInt("page.size");
	}

	protected <T> Query createQuery(CriteriaQuery<T> query) {
		return JPA.em().createQuery(query);
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return JPA.em().getCriteriaBuilder();
	}

	protected Query setPage(Query q, Integer pageNumber) {
		if (pageNumber != null) {
			if (pageNumber < 1)
				pageNumber = 1;
			q.setFirstResult((pageNumber - 1) * pageSize).setMaxResults(
					pageSize);
		}
		return q;
	}
}
