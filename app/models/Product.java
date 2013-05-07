package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import models.dynamicforms.Form;
import models.helpers.Dao;
import models.helpers.UserModel;
import org.hibernate.envers.Audited;
import play.data.validation.Constraints.Required;

@Entity
@Audited
public class Product extends UserModel {
	public final static Dao<Product, Long> dao = new Dao<Product, Long>(
			Product.class);

	@Required
	@NotNull
	@Column(unique = true)
	public int no;

	@Required
	@NotNull
	public String name;

	@Lob
	public String description;

	@ManyToMany
	public List<Ingredient> ingredients = new ArrayList<Ingredient>();

	@ManyToMany
	public List<Form> forms = new ArrayList<Form>();

	public String toString() {
		return name + " (" + no + ")";
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
		this.ingredients = Ingredient.dao.getReference(this.ingredients);
		this.forms = Form.dao.getReference(this.forms);
	}

	// Check that no is uniq
	public static boolean noExists(Integer no, Long id) {
		if (no == null)
			return false;
		CriteriaBuilder criteriaBuilder = dao.getCriteriaBuilder();
		CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		Root<Product> root = query.from(Product.class);
		query.select(criteriaBuilder.count(root));
		if (id != null)
			query.where(criteriaBuilder.equal(root.get(Product_.no), no),
					criteriaBuilder.notEqual(root.get(Product_.id), id));
		else
			query.where(criteriaBuilder.equal(root.get(Product_.no), no));
		Long count = dao.findLongBy(query);
		System.out.println(count);
		System.out.println(count > 0);
		if (count != null)
			return count > 0;
		return false;
	}
}
