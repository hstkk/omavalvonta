package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import models.dynamicforms.Form;
import models.helper.JpaModel;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
public class User extends JpaModel {
	@Required
	@NotNull
	public String username;

	@Required
	@NotNull
	public String firstname;

	@Required
	@NotNull
	public String surname;

	@Required
	@Email
	@NotNull
	public String email;

	@Required
	public Boolean isTeacher;

	public String schoolClass;

	public User() {
	}

	public static User findById(Long id) {
		if (id == null)
			return null;
		try {
			return JPA.em().find(User.class, id);
		} catch (Exception e) {
			return null;
		}
	}
}
