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
import models.helper.JpaModel;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Result extends JpaModel {

	// @Required
	@OneToOne
	@Valid
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

	public Result(Fieldset fieldset) {
		System.out.println(fieldset.fieldId);
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
		this.save();
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

	@SuppressWarnings("unchecked")
	public static List<Result> findByResults(Results results) {
		if (results == null || results.id == null)
			return null;
		try {
			List<Result> list = JPA
					.em()
					.createQuery(
							"from Result r where ResultsResult.results_id = ? and r.id = ResultsResult.result_id")
					.setParameter(1, results).getResultList();
			return list;
		} catch (Exception e) {
		}
		return null;
	}
}
