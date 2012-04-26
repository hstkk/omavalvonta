package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

//TODO
@Entity
public class Batch {
	//TODO generate
	@Id
	public String id;
	
	@Required
	public Date created;
	
	//TODO validate
	@Required
	@OneToOne
	public Product product;
}
