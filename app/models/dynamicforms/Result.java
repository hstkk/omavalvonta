package models.dynamicforms;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
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
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Audited
public class Result extends JpaModel implements Comparable<Result> {
	@Required
	@NotNull
	public Date updated;

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
	public String comment = null;

	public Boolean isDone = false;

	public Result() {
	}

	public Result(Fieldset fieldset) {
		if (fieldset.value != null && fieldset.comment != null) {
			this.field = Field.findById(fieldset.fieldId);
			this.comment = fieldset.comment;
			switch (field.fieldType) {
			case CHECKBOX:
				this.valueBoolean = Converter.stringToBool(fieldset.value);
				if (this.field.targetValue == null
						|| this.field.targetValue == this.valueBoolean)
					this.isDone = true;
				break;
			case DATE:
				this.valueDate = Converter.stringToDate(fieldset.value);
				this.isDone = true;
				break;
			case INT:
				this.valueInt = Converter.stringToInt(fieldset.value);
				if ((this.field.min == null || this.valueInt >= this.field.min)
						&& (this.field.max == null || this.valueInt <= this.field.max))
					this.isDone = true;
				break;
			case DOUBLE:
				this.valueDouble = Converter.stringToDouble(fieldset.value);
				if ((this.field.min == null || this.valueDouble >= this.field.min)
						&& (this.field.max == null || this.valueDouble <= this.field.max))
					this.isDone = true;

				break;
			case TEXT:
			case TEXTAREA:
				this.valueString = fieldset.value;
				if (!this.valueString.isEmpty())
					this.isDone = true;
				break;
			}
			if (!((field.fieldType == FieldType.TEXT || field.fieldType == FieldType.TEXTAREA) && this.valueString
					.isEmpty())) {
				if (this.user == null)
					this.isDone = false;
				if (fieldset.id != null) {
					this.id = fieldset.id;
					Result revision = Result.findById(this.id);
					if (revision != null)
						this.updated = revision.updated;
					this.update();
				} else
					this.save();
			}
		}
	}

	public Result(Field field) {
		this.field = field;
	}

	public String toString() {
		if (valueString != null)
			return valueString;
		else if (valueInt != null)
			return valueInt.toString();
		else if (valueDouble != null)
			return valueDouble.toString();
		else if (valueBoolean != null)
			if (valueBoolean)
				return "ok";
			else
				return "Ei ok";
		else if (valueDate != null)
			return new SimpleDateFormat("dd.MM.yyyy").format(valueDate)
					.toString();
		return "";
	}

	public List<Result> getHistory() {
		List<Result> history = new ArrayList<Result>();
		AuditReader auditReader = AuditReaderFactory.get(JPA.em());
		List<Number> revisions = auditReader.getRevisions(this.getClass(), id);
		for (Number revision : revisions) {
			Result result = auditReader.find(this.getClass(), id, revision);
			history.add(result);
		}
		return history;
	}

	@Override
	public int compareTo(Result o) {
		return field.id.compareTo(o.field.id);
	}

	public static Result findById(Long id) {
		try {
			if (id != null)
				return JPA.em().find(Result.class, id);
		} catch (Exception e) {
		}
		return null;
	}

	@PrePersist
	protected void onCreate() {
		updated = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}
}
