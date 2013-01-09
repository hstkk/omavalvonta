package models.helpers;

import java.util.Map;

import javax.persistence.Query;

import play.Play;
import play.db.jpa.JPA;

public class JpaHelper {
	protected final int pageSize;

	public JpaHelper() {
		this.pageSize = Play.application().configuration().getInt("page.size");
	}

	@SuppressWarnings("unchecked")
	protected Query bindParameters(Query q, Object... params) {
		if (params == null)
			return q;
		if (params.length == 1 && params[0] instanceof Map)
			return bindParameters(q, (Map<String, Object>) params[0]);
		for (int i = 0; i < params.length; i++)
			q.setParameter(i + 1, params[i]);
		return q;
	}

	protected Query bindParameters(Query q, Map<String, Object> params) {
		if (params == null)
			return q;
		for (String key : params.keySet())
			q.setParameter(key, params.get(key));
		return q;
	}

	protected Query createQuery(String query, Object[] params) {
		Query q = JPA.em().createQuery(query);
		return bindParameters(q, params);
	}

	protected Query orderBy(String query, String order, String by) {
		
		return createQuery(query, null);
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
