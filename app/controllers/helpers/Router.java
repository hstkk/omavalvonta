package controllers.helpers;

import play.mvc.Call;

public class Router implements RouterInterface {

	@Override
	public Call page() {
		return controllers.routes.Application.index();
	}

	@Override
	public Call show(Long id) {
		return page();
	}

}
