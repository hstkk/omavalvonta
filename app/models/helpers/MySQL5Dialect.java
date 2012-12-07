package models.helpers;

import java.sql.Types;

/**
 * Fix MySQL5 boolean type.
 */
public class MySQL5Dialect extends org.hibernate.dialect.MySQL5Dialect {
	public MySQL5Dialect() {
		super();
		registerColumnType(Types.BIT, "tinyint(1)");
	}
}