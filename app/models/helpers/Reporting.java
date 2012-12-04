package models.helpers;

import java.util.Date;
import java.util.List;

import models.Batch;
import models.Product;

import play.Play;
import play.data.format.Formats;
import play.db.jpa.JPA;

public class Reporting {
	@Formats.DateTime(pattern = "dd.MM.yyyy")
	public Date start;

	@Formats.DateTime(pattern = "dd.MM.yyyy")
	public Date end;

	public Product product;

	public static Long baches(Long productId) {
		Long count = null;
		try {
			count = (Long) JPA
					.em()
					.createQuery(
							"select count(*) from Batch where " + "product.id = ?")
					.setParameter(1, productId).getSingleResult();
		} catch (Exception e) {
		}
		return count;
	}

	public static Long finalProducts(Long productId) {
		Long count = null;
		try {
			count = (Long) JPA
					.em()
					.createQuery(
							"select count(amount) from FinalProduct where " + "product.id = ?")
					.setParameter(1, productId).getSingleResult();
		} catch (Exception e) {
		}
		return count;
	}
}
