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
		return Helper.getBadRequest();
	}

	@Override
	public Result onError(RequestHeader request, Throwable t) {
		t.printStackTrace();
		return Helper.getInternalServerError();
	}

	@Override
	public Result onHandlerNotFound(RequestHeader request) {
		return Helper.getNotFound();
	}
}
