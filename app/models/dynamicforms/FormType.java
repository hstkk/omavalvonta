package models.dynamicforms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import play.data.validation.Constraints.Required;
import models.helpers.JpaModel;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
@Entity
public class FormType extends JpaModel {
	@Required
	@NotNull
	@Column(unique = true)
	public String name;

	@Required
	@NotNull
	@Column(unique = true)
	public String slug;
}