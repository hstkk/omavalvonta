package utils;

import java.sql.Types;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * Fix MySQL5 boolean type.
 */
public class MySQL5DialectBooleanAsTinyint extends MySQL5Dialect {
	public MySQL5DialectBooleanAsTinyint() {
		super();
		registerColumnType(Types.BIT, "tinyint(1)");
	}
}