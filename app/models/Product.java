package models;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import models.dynamicforms.Form;
import models.helpers.JpaModel;
import models.helpers.Page;

import play.Play;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Product.
 * 
 * @author Sami Hostikka <dev@01.fi>
 */
@Entity
@Audited
public class Product extends JpaModel {

	/** The name. */
	@Required
	@NotNull
	@Column(unique = true)
	public String name;

	/** The slug. */
	@Required
	@NotNull
	@Column(unique = true)
	public String slug;

	/** The description. */
	@Lob
	public String description;

	// TODO cascade?
	/** The ingredients. */
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	public List<Ingredient> ingredients = new ArrayList<Ingredient>();

	// TODO cascade?
	// TODO necessary? hard code?
	/** The forms. */
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	public List<Form> forms = new ArrayList<Form>();

	/**
	 * Instantiates a new product.
	 */
	public Product() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}

	/**
	 * Find by id.
	 * 
	 * @param id
	 *            the id
	 * @return the product
	 */
	public static Product findById(Long id) {
		try {
			if (id != null)
				return JPA.em().find(Product.class, id);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Page.
	 * 
	 * @param index
	 *            the index
	 * @return the page
	 */
	public static Page page(int index) {
		try {
			int size = Play.application().configuration().getInt("page.size");
			if (index < 1)
				index = 1;
			Long rows = (Long) JPA.em()
					.createQuery("select count(*) from Product")
					.getSingleResult();
			List<Product> list = JPA.em()
					.createQuery("from Product p order by p.name asc")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
			if (rows != null && list != null && !list.isEmpty())
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Options.
	 * 
	 * @return the map
	 */
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
