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
public class Product {
	/** The id. */
	@Id
	@Column(updatable = false, unique = true)
	@Required
	@NotNull
	public Long id;

	/**
	 * Save.
	 * 
	 * @return true, if successful
	 */
	public boolean saveOrUpdate() {
		try {
			//JPA.em().persist(this);
			JPA.em().merge(this);
			return true;
		} catch (Exception e) {
			System.out.println("\n\n" + e + "\n\n");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Delete.
	 * 
	 * @return true, if successful
	 */
	public boolean delete() {
		try {
			JPA.em().remove(this);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Update.
	 * 
	 * @return true, if successful
	 *//*
	public boolean update() {
		try {
			JPA.em().merge(this);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}*/

	/** The name. */
	@Required
	@NotNull
	@Column(unique = true)
	public String name;

	/** The description. */
	@Lob
	public String description;

	// TODO cascade?
	/** The ingredients. */
	@ManyToMany(cascade = CascadeType.ALL)
	public List<Ingredient> ingredients = new ArrayList<Ingredient>();

	/** The forms. */
	@ManyToMany(cascade = CascadeType.ALL)
	public List<Form> forms = new ArrayList<Form>();

	/**
	 * Instantiates a new product.
	 */
	public Product() {
	}

	public Product(forms.Product form) {
		this.id = form.id;
		this.description = form.description;
		this.name = form.name;
		if (!form.formIds.isEmpty())
			for (Long id : form.formIds)
				if (id != null)
					forms.add(Form.crud.findById(id));
		if (!form.ingredientIds.isEmpty())
			for (Long id : form.ingredientIds)
				if (id != null)
					ingredients.add(Ingredient.crud.findById(id));
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
			long rows = (long) JPA.em()
					.createQuery("select count(*) from Product")
					.getSingleResult();
			List<Product> list = JPA.em()
					.createQuery("from Product p order by p.name asc")
					.setFirstResult((index - 1) * size).setMaxResults(size)
					.getResultList();
			if (rows > 0 && list != null && !list.isEmpty())
				return new Page(index, size, rows, list);
		} catch (Exception e) {
		}
		return new Page(index, 0, 0, null);
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
