package models.helpers;

import java.io.Serializable;
import javax.persistence.*;

@MappedSuperclass
public class Model<ID extends Serializable> {
	@Id
	@GeneratedValue
	@Column(updatable = false)
	public ID id;
}