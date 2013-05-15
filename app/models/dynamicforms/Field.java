package models.dynamicforms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
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
	@NotNull
	public String name;

	@Lob
	public String help;

	@Required
	@NotNull
	public boolean isRequired;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fieldset_id", insertable = false, updatable = false, nullable = false)
	public Fieldset fieldset;

	@Column(name = "fields_index", insertable = false, updatable = false)
	public Integer index;

	@Required
	@NotNull
	public Integer fieldType;

	@Transient
	public FieldType fieldTypeEnum;

	@Required
	@NotNull
	public boolean isSigned;

	public Double min = null;

	public Double max = null;

	public Boolean targetValue = null;

	public String toString() {
		return name;
	}

	@PrePersist
	@Override
	public boolean onCreate() {
		return super.onCreate();
	}

	@PreUpdate
	@Override
	public boolean onUpdate() {
		return super.onUpdate();
	}

	@PostLoad
	private void onPost() {
		if (this.fieldType != null)
			fieldTypeEnum = FieldType.setValue(this.fieldType);
	}
}