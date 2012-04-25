package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
public class Form {
	@Id
	@GeneratedValue
	public int id;
	
	//TODO
	//@Validate
	@OneToOne
	public Content content;
	
	@Required
	public List<Field> fields;
	
	@Required
	public Boolean isActive;
}
