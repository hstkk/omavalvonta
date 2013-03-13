package controllers.helpers;

import play.mvc.Call;

public interface RouterInterface {
	Call page();

	Call show(Long id);
}
