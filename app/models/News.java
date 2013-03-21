package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import models.helpers.Crud;
import models.helpers.UserModel;
import org.hibernate.envers.Audited;
import play.data.validation.Constraints.Required;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
@Entity(name = "News")
@Audited
public class News extends UserModel {

	public final static Crud<News, Long> crud = new Crud<News, Long>(News.class);

	@Column(name = "name")
	@Required
	@NotNull
	public String name;

	@Lob
	@Required
	@NotNull
	public String story;

	public String toString() {
		return name;
	}
}