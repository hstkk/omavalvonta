import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.notFound;
import play.GlobalSettings;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import utils.Helper;

/**
 * @author Sami Hostikka
 */
public class Global extends GlobalSettings {

	@Override
	public Result onBadRequest(RequestHeader request, String error) {
		String title = Helper.getMessage("http.400");
		String description = Helper.getMessage("http.400.description");
		return badRequest(views.html.error.render(title, description));
	}

	@Override
	public Result onError(RequestHeader request, Throwable t) {
		t.printStackTrace();
		String title = Helper.getMessage("http.500");
		String description = Helper.getMessage("http.500.description");
		return internalServerError(views.html.error.render(title, description));
	}

	@Override
	public Result onHandlerNotFound(RequestHeader request) {
		String title = Helper.getMessage("http.404");
		String description = Helper.getMessage("http.404.description");
		return notFound(views.html.error.render(title, description));
	}
}
