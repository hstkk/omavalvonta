package models;

import java.util.*;
import javax.persistence.*;

import models.dynamicforms.Form;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

//@Entity
public class Product {
	@Id
	@GeneratedValue
	public int id;
	
	@Required
	public String name;
	
	@Lob
	public String descrition;
	
	@Required
	@OneToOne
	public Form washProgram;
	
	@Required
	@OneToOne
	public Form productCard;
	
	@Required
	@OneToOne
	public Form purityMonitoring;
}
