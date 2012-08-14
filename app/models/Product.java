package models;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import models.dynamicforms.Form;
import models.helper.JpaModel;

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
	public String name;

	@Lob
	public String description;

	@Required
	@ManyToOne
	public Form washProgram;

	@Required
	@ManyToOne
	public Form productCard;

	@Required
	@ManyToOne
	public Form purityMonitoring;

	public Product() {
	}

	private void set() {
		if (this.washProgram.id == null)
			this.washProgram = null;
		else
			this.washProgram = Form.findById(this.washProgram.id);
		if (this.productCard.id == null)
			this.productCard = null;
		else
			this.productCard = Form.findById(this.productCard.id);
		if (this.purityMonitoring.id == null)
			this.purityMonitoring = null;
		else
			this.purityMonitoring = Form.findById(this.purityMonitoring.id);
	}

	public boolean save() {
		try {
			set();
			JPA.em().persist(this);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update() {
		try {
			set();
			JPA.em().merge(this);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String toString() {
		return name;
	}

	public static Product findById(Long id) {
		if (id == null)
			return null;
		try {
			return JPA.em().find(Product.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Product> findAll() {
		try {
			List<Product> product = JPA.em()
					.createQuery("from Product order by name").getResultList();
			return product;
		} catch (Exception e) {
			return null;
		}
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
