package models.helpers;

import javax.persistence.*;

@MappedSuperclass
public class Model<T> extends Crud<T, Long> {

	public Model(Class<T> clazz) {
		super(clazz);
	}

	@Id
	@GeneratedValue
	@Column(updatable = false)
	public Long id;
}