package models;

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
	@Email
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
}
