package models.dynamicforms;

import javax.persistence.Entity;

import models.helpers.Dao;
import models.helpers.UserModel;

import org.hibernate.envers.Audited;

//TODO
@Entity
@Audited
public class Form extends UserModel {
	public final static Dao<Form, Long> dao = new Dao<Form, Long>(Form.class);
}
