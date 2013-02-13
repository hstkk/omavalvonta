package models.dynamicforms;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.envers.Audited;

import models.helpers.Crud;
import models.helpers.UserModel;
import utils.*;

import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Fieldset extends UserModel {

	public Fieldset() {
	}

	public final static Crud<Fieldset, Long> crud = new Crud<Fieldset, Long>(
			Fieldset.class);

	@Required
	@NotNull
	public String name;

	@Lob
	public String description;

	@Required
	@NotNull
	public boolean isActive;

	@OneToMany(cascade = CascadeType.ALL)
	@IndexColumn(name = "position ", base = 1)
	public List<Field> fields = new ArrayList<Field>();

	@PrePersist
	@PreUpdate
	private void onPre() {
		this.fields = Field.crud.getReference(this.fields);
	}

	public String toString() {
		return name;
	}
}