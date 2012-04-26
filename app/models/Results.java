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
	public Date created;
	
	//TODO enum type
	@Required
	public int type;
	
	@Required
	public Boolean isReady;
	
	@Required
	public List<Result> results;
	
	@Required
	@OneToOne
	public Batch batch;
}
