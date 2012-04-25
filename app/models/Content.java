package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
public class Content {
	@Id
	@GeneratedValue
	public int id;
	
	@Required
	public String name;
	
	@Lob
	public String text;
}
