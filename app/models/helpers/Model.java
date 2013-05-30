package models.helpers;

import java.util.Date;
import javax.persistence.*;

import org.hibernate.envers.Audited;

@MappedSuperclass
public class Model implements ModelInterface {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false)
	public Long id;

	@Column(nullable = false)
	@Audited
	public Date lastModified;

	// Executed before save
	public boolean onCreate() {
		lastModified = new Date();
		return true;
	}

	// Executed before update
	public boolean onUpdate() {
		lastModified = new Date();
		return true;
	}
}