package models.dynamicforms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.Audited;

import play.data.validation.Constraints.Required;
import play.db.jpa.JPA;

import models.Term;
import models.helpers.Dao;
import models.helpers.UserModel;

@Entity
@Audited
public class Result extends UserModel {
	public final static Dao<Result, Long> dao = new Dao<Result, Long>(
			Result.class);

	@Required
	@NotNull
	@Valid
	@ManyToOne
	@JoinColumn(name = "id", insertable = false, updatable = false, nullable = false)
	public Results results;

	@Required
	@NotNull
	@Valid
	@ManyToOne
	public Field field;

	@Lob
	public String value;

	@Transient
	public Integer valueInt;

	@Transient
	public Double valueDouble;

	@Transient
	public Boolean valueBoolean;

	@Transient
	public Date valueDate;

	@Lob
	public String comment;

	@Valid
	@ManyToOne(cascade = CascadeType.ALL)
	public Term reason;

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
}
