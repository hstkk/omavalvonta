package models.dynamicforms;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import forms.dynamicforms.Fieldset;
import utils.Converter;

import models.User;
import models.dynamicforms.Field;
import models.helpers.JpaModel;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Result extends JpaModel {

	// @Required
	@OneToOne
	// @Valid
	// @NotNull
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

	@Lob
	public String comment;

	public Result() {
	}

	// TODO prevent null records
	public Result(Fieldset fieldset) {
		if (fieldset.value != null && fieldset.comment != null) {
			this.field = Field.findById(fieldset.fieldId);
			this.comment = fieldset.comment;
			switch (field.type) {
			case CHECKBOX:
				this.valueBoolean = Converter.stringToBool(fieldset.value);
				break;
			case DATE:
				this.valueDate = Converter.stringToDate(fieldset.value);
				break;
			case INT:
				this.valueInt = Converter.stringToInt(fieldset.value);
				break;
			case DOUBLE:
				this.valueDouble = Converter.stringToDouble(fieldset.value);
				break;
			case TEXT:
			case TEXTAREA:
				this.valueString = fieldset.value;
				break;
			}
			if (!((field.type == FieldType.TEXT || field.type == FieldType.TEXTAREA) && this.valueString
					.isEmpty())) {
				if (fieldset.id != null) {
					this.id = fieldset.id;
					this.update();
				} else
					this.save();
			}
		}
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
