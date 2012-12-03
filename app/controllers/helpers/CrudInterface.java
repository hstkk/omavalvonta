package controllers.helpers;

import play.mvc.Result;

public interface CrudInterface {
	Result page(int index);

	Result fresh();

	Result create();

	Result edit(Long id);

	Result update(Long id);
}