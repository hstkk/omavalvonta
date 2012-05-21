package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Content extends JpaModel {
	@Required
	@NotNull
	public String name;

	@Lob
	public String text;

	public Content(String name, String text) {
		this.name = name;
		this.text = text;
	}
}
