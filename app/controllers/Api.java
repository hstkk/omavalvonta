package controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import models.Batch;
import models.Product;
import models.dynamicforms.Form;
import play.Routes;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Api extends Controller {

	@Transactional(readOnly = true)
	public Result batches(Long productId, Long formId) {
		Product product = Product.dao.getReference(productId);
		Form form = Form.dao.getReference(formId);
		Map<String, String> options = Batch.optionsByProductAndForm(product, form);
		return ok(Json.toJson(options));
	}

	@Transactional(readOnly = true)
	public Result forms(Long productId) {
		Product product = Product.dao.findById(productId);
		LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
		if(product != null && product.forms != null) {
			for(Form form: product.forms)
				options.put(form.id.toString(), form.toString());
		}
		return ok(Json.toJson(options));
	}

	public Result javascriptRoutes() {
		response().setContentType("text/javascript");
		return ok(
			Routes.javascriptRouter("routes",
				controllers.routes.javascript.Api.forms(),
				controllers.routes.javascript.Api.batches()
			)
		);
	}
}
