package controllers;

import static play.data.Form.form;
import models.helpers.Dao;
import models.helpers.Page;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.F;
import play.mvc.Call;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import play.mvc.With;
import views.html.news.create;
import views.html.news.page;
import views.html.news.update;
import controllers.helpers.SecuredCrud;
import controllers.shib.Secured;
import controllers.shib.Session;

@With(Session.class)
@Authenticated(Secured.class)
public class News extends SecuredCrud<models.News> {
	public News() {
		super(
			F.Option.<Dao<models.News, Long>>Some(models.News.dao),
			F.Option.<Form<models.News>>Some(form(models.News.class)),
			F.Option.<Template1<Form<models.News>, Html>>Some(create.ref()),
			F.Option.<Template1<Page<models.News>, Html>>Some(page.ref()),
			F.Option.<Template1<models.News, Html>>None(),
			F.Option.<Template2<models.News, Form<models.News>, Html>>Some(update.ref())
		);
	}

	@Override
	public Call callPage() {
		return controllers.routes.News.page(1);
	}

	@Override
	@Transactional
	public Result create() {
		return super.create();
	}

	@Override
	@Transactional(readOnly = true)
	public Result page(int pageNumber) {
		return super.page(pageNumber);
	}

	@Override
	@Transactional
	public Result update(Long id) {
		return super.update(id);
	}
}
