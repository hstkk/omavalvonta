import play.*;
import play.i18n.Lang;
import play.i18n.Messages;
import play.mvc.*;
import play.mvc.Http.*;
import static play.mvc.Results.*;

/**
 * @author Sami Hostikka
 */
public class Global extends GlobalSettings {

	protected static Lang lang;

	private static Lang getLang() {
		if (lang == null)
			lang = Lang.preferred(Lang.availables());
		return lang;
	}

	@Override
	public Result onError(RequestHeader request, Throwable t) {
		String title = Messages.get(getLang(), "http.500");
		String description = Messages.get(getLang(), "http.500.description");
		return internalServerError(views.html.error.render(title, description));
	}

	@Override
	public Result onHandlerNotFound(RequestHeader request) {
		String title = Messages.get(getLang(), "http.404");
		String description = Messages.get(getLang(), "http.404.description");
		return notFound(views.html.error.render(title, description));
	}

	@Override
	public Result onBadRequest(RequestHeader request, String error) {
		String title = Messages.get(getLang(), "http.400");
		String description = Messages.get(getLang(), "http.400.description");
		return badRequest(views.html.error.render(title, description));
	}
}
