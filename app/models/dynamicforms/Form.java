package models.dynamicforms;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
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
	@NotNull(message = "")
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
	public boolean onCreate() {
		set();
		return super.onCreate();
	}

	@Override
	public boolean onUpdate() {
		set();
		return super.onUpdate();
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

	public static List<Form> findByFieldset(Fieldset fieldset) {
		CriteriaBuilder criteriaBuilder = dao.getCriteriaBuilder();
		CriteriaQuery<Form> query = criteriaBuilder
				.createQuery(Form.class);
		Root<Form> root = query.from(Form.class);
		Join<Form, Fieldset> join = root.join(Form_.fieldsets);
		query.where(criteriaBuilder.equal(join.get(Fieldset_.id), fieldset.id));
		return dao.findAllBy(query);
	}
}
