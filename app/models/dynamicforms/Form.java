package models.dynamicforms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import models.helpers.Crud;
import models.helpers.UserModel;

import org.hibernate.envers.Audited;

import play.data.validation.Constraints.Required;

//TODO
@Entity
@Audited
public class Form extends UserModel {
	public final static Crud<Form, Long> crud = new Crud<Form, Long>(Form.class);
}
