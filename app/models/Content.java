package models;

import java.util.*;
import javax.persistence.*;

import org.hibernate.envers.Audited;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Content {
	@Id
	@GeneratedValue
	public int id;
	
	@Required
	public String name;
	
	@Lob
	public String text;
}
