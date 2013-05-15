package models.dynamicforms;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.validation.constraints.NotNull;

import models.Product;
import models.helpers.Dao;
import models.helpers.UserModel;
import org.hibernate.envers.Audited;
import play.data.validation.Constraints.*;

@Entity
@Audited
public class Form extends UserModel {
	public final static Dao<Form, Long> dao = new Dao<Form, Long>(Form.class);

	@Required
	@NotNull
	public String name;

	@Lob
	public String description;

	@ManyToMany
	@OrderColumn(name = "form_index")
	public List<Fieldset> fieldsets = new ArrayList<Fieldset>();

	public String toString() {
		return name;
	}

	@Override
	public void onCreate() {
		set();
		super.onCreate();
	}

	@Override
	public void onUpdate() {
		set();
		super.onUpdate();
	}

	private void set() {
		this.fieldsets = Fieldset.dao.getReference(this.fieldsets);
	}

	public static Map<String, String> options(Product product) {
		LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
		if(product != null && product.forms != null) {
			for(Form form: product.forms)
				options.put(form.id.toString(), form.toString());
		}
		return options;
	}
}
