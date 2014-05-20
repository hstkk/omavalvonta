package controllers;

import static play.data.Form.form;
import models.Product;
import models.helpers.Dao;
import models.helpers.Page;
import play.api.templates.Html;
import play.api.templates.Template1;
import play.api.templates.Template2;
import play.data.Form;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.libs.F;
import play.mvc.Call;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import play.mvc.With;
import utils.Converter;
import views.html.products.create;
import views.html.products.page;
import views.html.products.update;
import controllers.helpers.SecuredCrud;
import controllers.shib.Secured;
import controllers.shib.Session;

@With(Session.class)
@Authenticated(Secured.class)
public class Products extends SecuredCrud<Product> {
	public Products() {
		super(
			F.Option.<Dao<Product, Long>>Some(Product.dao),
			F.Option.<Form<Product>>Some(form(Product.class)),
			F.Option.<Template1<Form<Product>, Html>>Some(create.ref()),
			F.Option.<Template1<Page<Product>, Html>>Some(page.ref()),
			F.Option.<Template1<Product, Html>>None(),
			F.Option.<Template2<Product, Form<Product>, Html>>Some(update.ref())
		);
	}

	@Override
	public Call callPage() {
		return controllers.routes.Products.page(1);
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

	// Check that no is uniq
	@Override
	public Form<Product> validateForm(Form<Product> filledForm, Long id) {
		String value = filledForm.field("no").valueOr("");
		if (!value.isEmpty()) {
			Integer no = Converter.stringToInt(value);
			if (Product.noExists(no, id))
				filledForm.reject("no", Messages.get("product.noExist"));
		}
		return filledForm;
	}
}
