package models.dynamicforms;

import javax.persistence.Entity;
import models.JpaModel;

/**
 * 
 * @author Sami Hostikka <dev@01.fi>
 * 
 */
@Entity
public class FormType extends JpaModel {
	public String name;
}