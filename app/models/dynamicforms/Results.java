package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import models.Batch;

import com.avaje.ebean.annotation.EnumValue;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

//@Entity
public class Results {
	@Id
	@GeneratedValue
	public int id;

	@Required
	public Date created;
	
	@Required
	@Enumerated(EnumType.STRING)
	@NotNull
	public FormType type;
	
	@Required
	public Boolean isReady;
	
	@Required
	public List<Result> results;
	
	@Required
	@OneToOne
	public Batch batch;
	
	public static Results findById(int id) {
		return JPA.em().find(Results.class, id);
	}
}