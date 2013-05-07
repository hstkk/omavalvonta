package models.dynamicforms;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.envers.Audited;
import play.data.validation.Constraints.Required;
import models.Batch;
import models.helpers.Dao;
import models.helpers.Model;

@Entity
@Audited
public class Results extends Model {
	public final static Dao<Results, Long> dao = new Dao<Results, Long>(
			Results.class);

	@Required
	@ManyToOne(cascade = CascadeType.ALL)
	@NotNull
	@Valid
	public Form form;

	@Required
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(updatable = false)
	@NotNull
	@Valid
	public List<Batch> batches = new ArrayList<Batch>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "results_id", nullable = false)
	@Valid
	@NotNull
	public List<Result> results = new ArrayList<Result>();

	public static play.data.Form<Results> getEmptyForm() {
		play.data.Form<Results> form = new play.data.Form<Results>(
				Results.class);
		return form;
	}
}
