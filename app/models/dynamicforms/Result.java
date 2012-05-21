package models.dynamicforms;

import java.util.*;
import javax.persistence.*;

import models.User;
import models.dynamicforms.Field;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

//@Entity
public class Result {
	@Id
	@GeneratedValue
	public int id;
	
	@Required
	@OneToOne
	public User user;
	
	//TODO pdf
	@Required
	@OneToOne
	public Field field;
	
	@Lob
	public String comment;
	
	public int valueInt;
	
	public Boolean valueBoolean;
	
	public Date valueDate;
	
	public String valueString;
	
	//TODO jne
}
