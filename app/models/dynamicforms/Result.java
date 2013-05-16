package models.dynamicforms;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.envers.Audited;

import play.data.format.Formats;
import play.data.validation.Constraints.Required;
import models.Term;
import models.helpers.Dao;
import models.helpers.UserModel;
import utils.Converter;
import utils.Formats.LocalizedDouble;

@Entity
@Audited(auditParents = { UserModel.class })
public class Result extends UserModel {
	public final static Dao<Result, Long> dao = new Dao<Result, Long>(
			Result.class);

	@ManyToOne
	@JoinColumn(name = "results_id", insertable = false, updatable = false, nullable = false)
	public Results results;

	@Required
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	public Field field;

	@Lob
	public String valueString;

	@Valid
	public Integer valueInt;

	@LocalizedDouble
	@Valid
	public Double valueDouble;

	@Valid
	public Boolean valueBoolean;

	@Formats.DateTime(pattern = "dd.MM.yyyy")
	@Valid
	public Date valueDate;

	@Lob
	public String comment;

	@ManyToOne(cascade = CascadeType.ALL)
	public Term reason;

	@Transient
	public Boolean ack;

	@Transient
	public Boolean isEmpty;

	public Result() {
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
			return Converter.doubleToString(valueDouble);
		else if (valueBoolean != null)
			return Converter.booleanToString(valueBoolean);
		else if (valueDate != null)
			return Converter.dateToString(valueDate);
		return "";
	}

	@PreUpdate
	@PrePersist
	private void onPre() {
		lastModified = new Date();
	}

	@PostLoad
	private void onLoad() {
		isEmpty = isEmpty();
	}

	@Transient
	private Field oldField;

	public Field getOldField() {
		return getOldField(null);
	}

	public Field getOldField(Results results) {
		if (oldField == null) {
			if (results != null)
				oldField = results.getField(this.id.toString());
			// TODO
			// else
			// oldField = dao.getFirstVersion(this);
			if (oldField == null)
				oldField = this.field;
		}
		return oldField;
	}

	public boolean isDone() {
		return isDone(null);
	}

	public boolean isDone(Results results) {
		return isValid(getOldField(results));
	}

	public boolean isEmpty() {
		return ((valueString == null || valueString.isEmpty())
				&& valueInt == null && valueDouble == null
				&& valueBoolean == null && valueDate == null
				&& (comment == null || comment.isEmpty()) && reason == null);
	}

	public boolean isValid(Field _field) {
		if (field != null) {
			if (field.isRequired && toString().isEmpty())
				return false;
			switch (_field.fieldTypeEnum) {
			case CHECKBOX:
				return (valueBoolean != null && (_field.targetValue == null || _field.targetValue == valueBoolean));
			case DATE:
				return (this.valueDate != null);
			case DOUBLE:
				return (valueDouble != null
						&& (_field.min == null || valueDouble >= _field.min) && (_field.max == null || valueDouble <= _field.max));
			case INT:
				return (valueInt != null
						&& (_field.min == null || valueInt >= _field.min) && (_field.max == null || valueInt <= _field.max));
			case TEXT:
			case TEXTAREA:
				return (this.valueString != null && !this.valueString.isEmpty());
			}
		}
		return false;
	}
}
