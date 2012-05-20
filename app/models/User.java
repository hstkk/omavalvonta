package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

//TODO pdf
//@Entity
public class User {
	@Required
	public String username;
	
	@Required
	public String firstname;
	
	@Required
	public String surname;
	
	@Required
	@Email
	public String email;
	
	@Required
	public Boolean isTeacher;
	
	public String schoolClass;
}
