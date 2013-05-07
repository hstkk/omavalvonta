package models.helpers;

import java.util.Date;
import javax.persistence.*;

import org.hibernate.envers.NotAudited;

@MappedSuperclass
public class Model implements ModelInterface {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false)
	public Long id;

	@NotAudited
	@Column(nullable = false)
	public Date lastModified;

	// Executed before save
	public void onCreate() {
		lastModified = new Date();
	}

	// Executed before update
	public void onUpdate() {
		lastModified = new Date();
	}

	// Executed before load
	// public void onLoad() {
	// }
}