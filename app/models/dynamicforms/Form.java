package models.dynamicforms;

import java.util.*;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import models.Content;
import models.JpaModel;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
public class Form extends JpaModel {
	@OneToOne
	@Valid
	public Form basedOn;
	
	@Required
	@Valid
	@NotNull
	@OneToOne
	public Content content;
	
	@Required
	@Valid
	@OneToMany
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
