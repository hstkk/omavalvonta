package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
public class Result {
	@Id
	@GeneratedValue
	public int id;
	
	@Required
	public User user;
	
	//TODO pdf
	@Required
	public Field field;
	
	@Lob
	public String comment;
	
	public int valueInt;
	
	public Boolean valueBoolean;
	
	public Date valueDate;
	
	//TODO jne
}
