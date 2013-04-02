package controllers.helpers;

import play.mvc.Call;
import play.mvc.Result;

public interface CrudInterface {
	Call callPage();

	Call callShow(Long id);

	Result create();

	Result edit(Long id);

	Result fresh();

	Result page(int pageNumber);

	Result show(Long id);

	Result update(Long id);
}