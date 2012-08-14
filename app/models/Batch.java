package models;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import models.dynamicforms.Form;
import models.helper.JpaModel;

import org.hibernate.envers.Audited;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Batch extends JpaModel {
	@Required
	@NotNull
	public Date created = new Date();

	@Required
	// @Valid
	@ManyToOne
	public Product product;

	@Required
	public Boolean isReady = false;

	public Batch() {
	}

	private void set() {
		if (this.product.id == null)
			this.product = null;
		else
			this.product = Product.findById(this.product.id);
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
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Erä ");
		stringBuilder.append(this.id);
		stringBuilder.append(", ");
		stringBuilder.append(new SimpleDateFormat("HH:mm dd.MM.yyyy").format(
				this.created).toString());
		stringBuilder.append(", ");
		stringBuilder.append(product.name);
		return stringBuilder.toString();
	}
	
	public String createdToString() {
		return new SimpleDateFormat("HH:mm dd.MM.yyyy").format(
				this.created).toString();
	}

	public static Batch findById(Long id) {
		if (id == null)
			return null;
		try {
			return JPA.em().find(Batch.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Batch> findAll() {
		try {
			List<Batch> batch = JPA.em()
					.createQuery("from Batch order by name").getResultList();
			return batch;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Batch> findReady() {
		try {
			List<Batch> batch = JPA.em()
					.createQuery("from Batch where isReady = true order by created desc")
					.getResultList();
			return batch;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Batch> findUnready() {
		try {
			List<Batch> batch = JPA
					.em()
					.createQuery("from Batch where isReady != true order by created desc")
					.getResultList();
			return batch;
		} catch (Exception e) {
			return null;
		}
	}
}