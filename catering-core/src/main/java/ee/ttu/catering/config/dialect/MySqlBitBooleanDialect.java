package ee.ttu.catering.config.dialect;

import java.sql.Types;

import org.hibernate.dialect.MySQL5InnoDBDialect;

public class MySqlBitBooleanDialect extends MySQL5InnoDBDialect {
	
	public MySqlBitBooleanDialect() {
        super();
        registerColumnType(Types.BOOLEAN, "bit");
    }       
}