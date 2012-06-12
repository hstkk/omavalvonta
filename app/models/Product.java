package models;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import models.dynamicforms.Form;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Product extends JpaModel {
	@Required
	@NotNull
	public String name;

	@Lob
	public String descrition;

	@Required
	@OneToOne
	public Form washProgram;

	@Required
	@OneToOne
	public Form productCard;

	@Required
	@OneToOne
	public Form purityMonitoring;

	public Product() {
	}
	
	public String toString(){
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
			List<Product> product = JPA.em().createQuery("from Product order by name")
					.getResultList();
			return product;
		} catch (Exception e) {
			return null;
		}
	}
}
