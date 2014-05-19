import java.util.Locale;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.api.mvc.EssentialFilter;
import play.data.format.Formatters;
import play.filters.csrf.CSRFFilter;
import play.libs.F.Promise;
import play.mvc.Http.RequestHeader;
import play.mvc.SimpleResult;
import utils.Formats.DoubleFormatter;
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
	public Promise<SimpleResult> onBadRequest(RequestHeader request,
			String error) {
		return Promise.<SimpleResult>pure((SimpleResult) Helper.getBadRequest());
	}

	@Override
	public Promise<SimpleResult> onError(RequestHeader request, Throwable t) {
		Logger.warn("500", t);
		return Promise.<SimpleResult>pure((SimpleResult) Helper.getInternalServerError());
	}

	@Override
	public Promise<SimpleResult> onHandlerNotFound(RequestHeader request) {
		return Promise.<SimpleResult>pure((SimpleResult) Helper.getNotFound());
	}

	@Override
	public void onStart(Application app) {
		Formatters.register(Double.class, new DoubleFormatter());
		super.onStart(app);
		Logger.info("Locale " + Locale.getDefault().toString());
	}
}
