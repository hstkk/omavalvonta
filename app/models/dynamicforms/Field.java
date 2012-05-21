package models.dynamicforms;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.envers.Audited;

import models.JpaModel;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
public class Field extends JpaModel {
	@Required
	@Enumerated(EnumType.STRING)
	@NotNull
	public FieldType type;

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
	public boolean isSigned;

	public Double min;

	public Double max;

	public Field() {
	}
	
	public Field(forms.dynamicforms.Field field){
		this.name = field.name;
		this.help = field.help;
		this.type = field.type;
		this.isRequired = field.isRequired;
		this.isSigned = field.isSigned;
		this.min = field.min;
		this.max = field.max;
		this.save();
	}

	/**
	 * Finds field by field id.
	 * 
	 * @param Fields
	 *            id.
	 * @return Field object if find is successful else null.
	 */
	public static Field findById(int id) {
		try {
			return JPA.em().find(Field.class, id);
		} catch (Exception e) {
			return null;
		}
	}
}