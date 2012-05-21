package models.dynamicforms;

import java.util.*;
import javax.persistence.*;

import models.Content;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
public class Form {
	@Id
	@GeneratedValue
	public int id;
	
	@OneToOne
	public Form basedOn = null;
	
	@Required
	public String name;
	
	@Lob
	public String text;
	
	//TODO
	@Required
	@OneToMany(mappedBy="form")
	@JoinTable
	(
		name="FormField",
		joinColumns={ @JoinColumn(name="form_id", referencedColumnName="id") },
		inverseJoinColumns={ @JoinColumn(name="field_id", referencedColumnName="id", unique=true) }
	)
	public List<Field> fields;
	
	@Required
	public Boolean isActive;
}
