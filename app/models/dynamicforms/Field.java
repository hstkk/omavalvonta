package models.dynamicforms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
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
	@JoinColumn(name = "fieldset_id")
	public Fieldset fieldset;

	@Column(name = "fields_index")
	public Integer index;

	@Required
	@NotNull
	public Integer fieldType;

	@Transient
	public FieldType fieldTypeEnum;

	@Required
	@NotNull
	public boolean isSigned;

	public Double min;

	public Double max;

	public Boolean targetValue;

	public String toString() {
		return name;
	}

	@PostLoad
	private void onPost() {
		if (this.fieldType != null)
			fieldTypeEnum = FieldType.setValue(this.fieldType);
	}

	public void setFieldset(Fieldset fieldset) {
		this.fieldset = fieldset;
		if (this.fieldset != null)
			this.index = this.fieldset.fields.indexOf(this);
	}
}