package models.dynamicforms;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import models.JpaModel;
import models.User;
import models.dynamicforms.Field;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Result extends JpaModel {

	@Required
	@OneToOne
	@Valid
	@NotNull
	@NotAudited
	public User user;

	@Required
	@OneToOne
	@Valid
	@NotNull
	public Field field;

	@Lob
	public String valueString = null;

	public Integer valueInt = null;

	public Double valueDouble = null;

	public Boolean valueBoolean = null;

	public Date valueDate = null;

	public Result() {
	}

	public String toString() {
		if (valueString != null)
			return valueString;
		else if (valueInt != null)
			return valueInt.toString();
		else if (valueDouble != null)
			return valueDouble.toString();
		else if (valueBoolean != null)
			return valueBoolean.toString();
		else if (valueDate != null)
			return new SimpleDateFormat("dd.MM.yyyy").format(valueDate)
					.toString();
		return "";
	}
}
