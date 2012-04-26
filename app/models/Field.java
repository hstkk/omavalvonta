package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
public class Field {
	@Id
	@GeneratedValue
	public int id;
	
	//TODO enum
	@Required
	public int type;
	
	@Required
	public String name;
	
	public String help;
	
	@Required
	public Boolean isRequired;
	
	@Required
	public Boolean isSigned;
	
	public double softMin;
	
	public double softMax;
	
	public double hardMin;
	
	public double hardMax;
}
