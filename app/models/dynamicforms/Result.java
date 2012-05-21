package models.dynamicforms;

import java.util.*;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import models.JpaModel;
import models.User;
import models.dynamicforms.Field;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

//@Entity
//@Audited
public class Result extends JpaModel {

	@Required
	@OneToOne
	@Valid
	@NotNull
	public User user;

	@Required
	@OneToOne
	@Valid
	@NotNull
	public Field field;

	@Lob
	public String comment;

	public int valueInt;

	public Double valueDouble;

	public Boolean valueBoolean;

	public Date valueDate;

	public String valueString;

	public Result() {
	}
}