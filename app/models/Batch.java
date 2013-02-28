package models;

import javax.persistence.Entity;

import models.helpers.Crud;
import models.helpers.UserModel;

import org.hibernate.envers.Audited;

//TODO
@Entity
@Audited
public class Batch extends UserModel {
	public final static Crud<Batch, Long> crud = new Crud<Batch, Long>(Batch.class);
}
