package models.helpers;

import javax.persistence.*;

@MappedSuperclass
public class Model {
	@Id
	@GeneratedValue
	@Column(updatable = false)
	public Long id;
}