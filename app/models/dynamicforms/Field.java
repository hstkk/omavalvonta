package models.dynamicforms;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import models.helpers.Dao;
import models.helpers.UserModel;
import org.hibernate.envers.Audited;
import play.data.validation.Constraints.*;
import play.i18n.Messages;

@Entity
@Audited
public class Field extends UserModel {
	public final static Dao<Field, Long> dao = new Dao<Field, Long>(Field.class);

	@Required
	@NotNull
	public String name;

	@Lob
	public String help;

	@Required
	@NotNull
	public boolean isRequired;

	@Required
	@NotNull
	@Valid
	@ManyToOne
	@JoinColumn(name = "id", insertable = false, updatable = false, nullable = false)
	public Fieldset fieldset;

	@Transient
	public FieldType fieldTypeEnum;

	@Required
	@NotNull
	public Integer fieldType;

	@Required
	@NotNull
	public boolean isSigned;

	public Double min;

	public Double max;

	public Boolean targetValue;

	public String toString() {
		return name;
	}

	@PrePersist
	@PreUpdate
	private void onPre() {
		if (fieldTypeEnum != null)
			fieldType = fieldTypeEnum.getValue();
	}

	@PostLoad
	private void intToEnum() {
		if (fieldType != null)
			fieldTypeEnum = FieldType.setValue(this.fieldType);
	}

	public static Map<String, String> targetOptions() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("true", Messages.get("boolean.true"));
		map.put("false", Messages.get("boolean.false"));
		return map;
	}
}
