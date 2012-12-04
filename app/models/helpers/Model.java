package models.helpers;

import javax.persistence.*;

@MappedSuperclass
public class Model<T> extends Crud<T, Long> {

	public Model(Class<T> clazz, String table) {
		super(clazz, table);
	}

	@Id
	@GeneratedValue
	@Column(updatable = false)
	public Long id;

	// TODO
	public void fill(T t) {
	}
}