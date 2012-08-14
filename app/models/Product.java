package models;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import models.dynamicforms.Form;
import models.dynamicforms.FormType;
import models.helpers.JpaModel;
import models.helpers.Page;

import play.Play;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;
import utils.Converter;

@Entity
@Audited
public class Product extends JpaModel {
	@Required
	@NotNull
	@Column(unique = true)
	public String name;

	@Required
	@NotNull
	@Column(unique = true)
	public String slug;

	@Lob
	public String description;

	// TODO cascade?
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	public List<Ingredient> ingredients = new ArrayList<Ingredient>();

	// TODO cascade?
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@MapKey
	public Map<FormType, Form> forms = new HashMap<FormType, Form>();

	public Product() {
	}

	public String toString() {
		return name;
	}

	public static Product findById(Long id) {
		try {
			if (id != null)
				return JPA.em().find(Product.class, id);
		} catch (Exception e) {
		}
		return null;
	}

	public static Page page(int index) {
		try {
			int size = Play.application().configuration().getInt("page.size");
			if (index < 1)
				index = 1;
			Integer rows = (Integer) JPA.em()
					.createQuery("select count(*) from Product")
					.getSingleResult();
			List<Product> list = JPA.em()
					.createQuery("from Product p order by p.name asc")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
			if (rows != null || list != null)
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> options() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		try {
			List<Product> products = JPA.em()
					.createQuery("from Product order by name").getResultList();
			for (Product product : products)
				map.put(product.id.toString(), product.toString());
			return map;
		} catch (Exception e) {
			return map;
		}
	}
}
