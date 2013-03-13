package controllers.helpers;

import play.mvc.Call;

public class UserRouter extends Router {
	public Call ack(Long id) {
		return show(id);
	}
}
