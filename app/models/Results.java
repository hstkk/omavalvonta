package models;

import java.util.*;

import javax.persistence.*;

import com.avaje.ebean.annotation.EnumValue;

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
	
	public enum type {
		@EnumValue("A")
		WASHPROGRAM,
		@EnumValue("B")
		PRODUCTCARD,
		@EnumValue("C")
		PURITYMONITORING,
	}
	
	@Required
	public Boolean isReady;
	
	@Required
	public List<Result> results;
	
	@Required
	@OneToOne
	public Batch batch;
}
