package models.helpers;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import org.hibernate.envers.Audited;
import models.User;

@MappedSuperclass
public class UserModel extends Model {
	@Audited
	@ManyToOne
	public User user;
}
