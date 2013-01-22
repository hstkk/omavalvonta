package models.helpers;

import javax.persistence.*;

import org.hibernate.envers.RevisionTimestamp;

@MappedSuperclass
public class Model /*implements ModelInterface*/ {
	@Id
	@GeneratedValue
	@Column(updatable = false)
	public Long id;

	//@Version
	//Timestamp timestamp;
	@RevisionTimestamp
	long timestamp;
}