package models;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import models.helpers.Crud;
import models.helpers.Model;
import play.data.validation.Constraints.*;

@Entity
@Audited
public class User extends Model {
	public final static Crud<User, Long> crud = new Crud<User, Long>(User.class);

	public String firstName;

	public String lastName;

	@Required
	@NotNull
	@Lob
	public String email;

	public String role;

	public User() {
	}

	public String toString() {
		if (firstName != null && lastName != null)
			return firstName + " " + lastName;
		return email;
	}

	public static User findByEmail(String email) {
		if (email == null)
			return null;
		CriteriaBuilder criteriaBuilder = crud.getCriteriaBuilder();
		CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.where(criteriaBuilder.equal(root.get(User_.email), email));
		return crud.findBy(query);
	}
}
