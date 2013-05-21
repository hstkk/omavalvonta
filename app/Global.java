import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.api.mvc.EssentialFilter;
import play.data.format.Formatters;
import play.filters.csrf.CSRFFilter;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import utils.Formats.*;
import utils.Helper;

/**
 * @author Sami Hostikka
 */
public class Global extends GlobalSettings {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T extends EssentialFilter> Class<T>[] filters() {
		Class[] filters = { CSRFFilter.class };
		return filters;
	}

	@Override
	public Result onBadRequest(RequestHeader request, String error) {
		return Helper.getBadRequest();
	}

	@Override
	public Result onError(RequestHeader request, Throwable t) {
		Logger.warn("500", t);
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
