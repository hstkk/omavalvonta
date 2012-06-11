package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;

@Entity
@Audited
public class Batch extends JpaModel {
	@Required
	@NotNull
	public Date created;

	@Required
	@Valid
	@OneToOne
	public Product product;

	public Batch() {
	}
}
