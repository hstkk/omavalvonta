package models;

import java.util.*;
import javax.persistence.*;

//import org.hibernate.type.EnumType;
import javax.persistence.EnumType;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
public class Field {
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
	public Boolean isRequired;

	@Required
	public Boolean isSigned;

	public Double softMin = null;

	public Double softMax = null;

	public Double hardMin = null;

	public Double hardMax = null;

	public static Field findById(int id) {
		try {
			return JPA.em().find(Field.class, id);
		} catch (Exception e) {
			return null;
		}
	}
}