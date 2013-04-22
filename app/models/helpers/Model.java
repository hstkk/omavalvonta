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

	@PrePersist
	public void onCreate() {
		lastModified = new Date();
		set();
	}

	@PreUpdate
	public void onUpdate() {
		lastModified = new Date();
		set();
	}

	public void set() {
	}

	public void get() {
	}
}