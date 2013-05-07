package controllers;

import models.Product;
import static play.data.Form.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.mvc.Call;
import play.mvc.Result;
import play.mvc.With;
import play.mvc.Security.Authenticated;
import views.html.products.*;
import controllers.helpers.SecuredCrud;
import controllers.shib.Secured;
import controllers.shib.Session;
import utils.Converter;

@With(Session.class)
@Authenticated(Secured.class)
public class Products extends SecuredCrud<Product> {
	public Products() {
		super(Product.dao, form(Product.class), create.ref(), page.ref(), null,
				update.ref());
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
