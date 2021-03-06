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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
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
@Audited
public class Result extends UserModel {
	public final static Dao<Result, Long> dao = new Dao<Result, Long>(
			Result.class);

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "results_id", insertable = false, updatable = false, nullable = false)
	public Results results;

	@Required
	@NotNull(message = "")
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

	public boolean isDone() {
		return isDone(null);
	}

	public boolean isDone(Field _field) {
		return isValid(_field);
	}

	public boolean isEmpty() {
		return ((valueString == null || valueString.isEmpty())
				&& valueInt == null && valueDouble == null
				&& valueBoolean == null && valueDate == null
				&& (comment == null || comment.isEmpty()) && reason == null);
	}

	public boolean isValid(Field _field) {
		if (_field != null) {
			if (_field.isRequired && isEmpty())
				return false;
			switch (FieldType.setValue(_field)) {
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

	public static Result findByResultAndId(Long resultsId, Long resultId) {
		if (resultsId == null || resultId == null)
			return null;
		CriteriaBuilder criteriaBuilder = dao.getCriteriaBuilder();
		CriteriaQuery<Result> query = criteriaBuilder.createQuery(Result.class);
		Root<Result> root = query.from(Result.class);
		Join<Result, Results> join = root.join(Result_.results);
		query.where(criteriaBuilder.equal(join.get(Results_.id), resultsId),
				criteriaBuilder.equal(root.get(Result_.id), resultId));
		return dao.findBy(query);
	}
}
