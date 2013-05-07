package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import models.helpers.Dao;
import models.helpers.Model;
import org.hibernate.envers.Audited;
import play.data.validation.Constraints.Required;

@Entity
@Audited
public class User extends Model {
	public final static Dao<User, Long> dao = new Dao<User, Long>(User.class);

	public String firstName;

	public String lastName;

	@Required
	@NotNull
	@Column(unique = true)
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
		CriteriaBuilder criteriaBuilder = dao.getCriteriaBuilder();
		CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.where(criteriaBuilder.equal(root.get(User_.email), email));
		return dao.findBy(query);
	}

	public static User findByEmail(User user) {
		return findByEmail(user.email);
	}
}
