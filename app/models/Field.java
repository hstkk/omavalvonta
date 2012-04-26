package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.annotation.EnumValue;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
public class Field {
	@Id
	@GeneratedValue
	public int id;
	
	public enum type {
		@EnumValue("A")
		TEXT,
		@EnumValue("B")
		INT,
		@EnumValue("C")
		DOUBLE,
		@EnumValue("D")
		DATE,
		@EnumValue("E")
		CHECKBOX,
		@EnumValue("F")
		DATETIME,
		@EnumValue("G")
		TEXTAREA,
	}
	
	@Required
	public String name;
	
	public String help;
	
	@Required
	public Boolean isRequired;
	
	@Required
	public Boolean isSigned;
	
	public double softMin;
	
	public double softMax;
	
	public double hardMin;
	
	public double hardMax;
}
