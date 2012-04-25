package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
public class Results {
	@Id
	@GeneratedValue
	public int id;
	
	@Required
	@OneToOne
	public Form form;
	
	@Required
	public Boolean isReady;
	
	@Required
	public List<Result> results;
	
	//TODO erä
	//@Required
	//@OneToOne
	//public Batch batch;
}
