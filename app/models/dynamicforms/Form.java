package models.dynamicforms;

import javax.persistence.Entity;

import models.helpers.Crud;
import models.helpers.UserModel;

import org.hibernate.envers.Audited;

//TODO
@Entity
@Audited
public class Form extends UserModel {
	public final static Crud<Form, Long> crud = new Crud<Form, Long>(Form.class);
}
