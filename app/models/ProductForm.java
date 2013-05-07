package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.envers.Audited;
import models.dynamicforms.Form;

@Entity
@Table(name = "Product_Form")
@Audited
public class ProductForm {
	@Id
	Long id;

	@JoinColumn(name = "product_id", updatable = false, nullable = false)
	@ManyToOne
	public Product product;

	@JoinColumn(name = "form_id", updatable = false, nullable = false)
	@ManyToOne
	public Form form;

	public ProductForm(Product product, Form form) {
		this.product = product;
		this.form = form;
	}

	public static List<ProductForm> prepare(Product product,
			List<ProductForm> forms) {
		List<ProductForm> productForms = new ArrayList<ProductForm>();
		for (ProductForm productForm : forms) {
			Form form = Form.dao.getReference(productForm.form);
			if (form != null)
				productForms.add(new ProductForm(product, form));
		}
		return productForms;
	}
}