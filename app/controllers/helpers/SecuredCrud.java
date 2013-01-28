package controllers.helpers;

import controllers.shib.Secured;
import models.helpers.Page;
import models.helpers.UserModel;
import play.api.mvc.Call;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

@Security.Authenticated(Secured.class)
public class SecuredCrud<T extends UserModel> extends Crud<T> {
	public SecuredCrud(models.helpers.Crud<T, Long> CRUD, Form<T> FORM,
			Template2<Long, Form<T>, Html> UPDATE,
			Template1<Form<T>, Html> CREATE, Template1<Page<T>, Html> PAGE,
			Template1<T, Html> SHOW, Call REDIRECT) {
		super(CRUD, FORM, UPDATE, CREATE, PAGE, SHOW, REDIRECT);
	}

	@Override
	@Transactional
	public Result create() {
		// TODO Auto-generated method stub
		return super.create();
	}

	@Override
	@Transactional(readOnly = true)
	public Result edit(Long id) {
		// TODO Auto-generated method stub
		return super.edit(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Result fresh() {
		// TODO Auto-generated method stub
		return super.fresh();
	}

	@Override
	@Transactional(readOnly = true)
	public Result page(int pageNumber, String order, String by) {
		// TODO Auto-generated method stub
		return super.page(pageNumber, order, by);
	}

	@Override
	@Transactional(readOnly = true)
	public Result show(Long id) {
		// TODO Auto-generated method stub
		return super.show(id);
	}

	@Override
	@Transactional
	public Result update(Long id) {
		// TODO Auto-generated method stub
		return super.update(id);
	}
}
