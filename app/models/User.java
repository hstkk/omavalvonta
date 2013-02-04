package models;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;
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
		String query = "from User where email = ?1";
		String[] params = new String[1];
		params[1] = email;
		return crud.findBy(query, params);
	}
}
