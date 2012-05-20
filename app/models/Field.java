package models;

import java.util.*;
import javax.persistence.*;
import javax.persistence.EnumType;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
public class Field{
	@Id
	@GeneratedValue
	public int id;

	@Required
	@Enumerated(EnumType.STRING)
	public FieldType type;

	@Required
	public String name;

	public String help;

	@Required
	public boolean isRequired;

	@Required
	public boolean isSigned;

	public Double softMin = null;

	public Double softMax = null;

	public Double hardMin = null;

	public Double hardMax = null;

	public Field(String name, String help, FieldType type, boolean isRequired,
			boolean isSigned, Double softMin, Double softMax, Double hardMin,
			Double hardMax) {
		this.name = name;
		this.help = help;
		this.type = type;
		this.isRequired = isRequired;
		this.isSigned = isSigned;
		this.softMin = softMin;
		this.softMax = softMax;
		this.hardMin = hardMin;
		this.hardMax = hardMax;
		//this.save();
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