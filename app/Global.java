import play.Application;
import play.GlobalSettings;
import play.data.format.Formatters;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import utils.Formats.*;
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

	@Override
	public void onStart(Application app) {
		Formatters.register(Double.class, new DoubleFormatter());
		super.onStart(app);
	}
}
