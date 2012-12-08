package controllers.helpers;

import play.mvc.Result;

public interface CrudInterface {
	Result create();

	Result edit(Long id);

	Result fresh();

	Result page(int pageNumber, String order, String by);

	Result show(Long id);

	Result update(Long id);
}