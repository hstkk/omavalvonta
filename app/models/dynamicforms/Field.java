package models.dynamicforms;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import models.helpers.Dao;
import models.helpers.Model;
import org.hibernate.envers.Audited;
import play.data.validation.Constraints.*;

@Entity
@Audited
public class Field extends Model {
	public final static Dao<Field, Long> dao = new Dao<Field, Long>(Field.class);

	@Required
	@NotNull(message = "")
	public String name;

	@Lob
	public String help;

	@Required
	@NotNull(message = "")
	public boolean isRequired;

	@Required
	@NotNull(message = "")
	public boolean isImportant;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fieldset_id", insertable = false, updatable = false, nullable = false)
	public Fieldset fieldset;

	@Required
	@NotNull(message = "")
	public Integer fieldType;

	public Double min = null;

	public Double max = null;

	public Boolean targetValue = null;

	public String toString() {
		return name;
	}

	@PrePersist
	public void preCreate() {
		super.onCreate();
	}

	@PreUpdate
	public void preUpdate() {
		super.onUpdate();
	}
}