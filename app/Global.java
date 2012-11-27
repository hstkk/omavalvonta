import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import static play.mvc.Results.*;

/**
 * @author Sami Hostikka
 */
public class Global extends GlobalSettings {
	@Override
	public Result onError(RequestHeader request, Throwable t) {
		return internalServerError(views.html.errors.internalServerError
				.render());
	}

	@Override
	public Result onHandlerNotFound(RequestHeader request) {
		return notFound(views.html.errors.notFound.render());
	}

	@Override
	public Result onBadRequest(RequestHeader request, String error) {
		return badRequest(views.html.errors.badRequest.render());
	}
}
