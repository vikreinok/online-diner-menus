package ee.ttu.catering.config.dialect;

import org.hibernate.engine.jdbc.dialect.spi.BasicDialectResolver;

public class MySqlDialetResolver extends BasicDialectResolver {

	private static final long serialVersionUID = 1L;

	public MySqlDialetResolver() {
		super("MySQL", MySqlBitBooleanDialect.class);
	}
}