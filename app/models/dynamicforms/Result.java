package models.dynamicforms;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.envers.Audited;
import play.data.validation.Constraints.Required;
import models.Term;
import models.helpers.Dao;
import models.helpers.UserModel;

@Entity
@Audited
public class Result extends UserModel {
	public final static Dao<Result, Long> dao = new Dao<Result, Long>(
			Result.class);

	@ManyToOne
	@JoinColumn(name = "results_id", insertable = false, updatable = false, nullable = false)
	public Results results;

	@Required
	@NotNull
	@Valid
	@ManyToOne
	public Field field;

	@Lob
	public String valueString;

	public Integer valueInt;

	public Double valueDouble;

	public Boolean valueBoolean;

	public Date valueDate;

	@Lob
	public String comment;

	@Valid
	@ManyToOne(cascade = CascadeType.ALL)
	public Term reason;
}
