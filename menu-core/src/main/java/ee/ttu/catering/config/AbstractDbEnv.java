package ee.ttu.catering.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

public abstract class AbstractDbEnv extends WebAppConfig{

	protected static String TYPE = "test.";
	
	protected static final String DATABASE_DRIVER = "db.driver";
	protected static final String DATABASE_PASSWORD = "db.password";
	protected static final String DATABASE_URL = "db.url";
	protected static final String DATABASE_USERNAME = "db.username";

	protected static final String HIBERNATE_DIALECT = "hibernate.dialect";
    protected static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    protected static final String HIBERNATE_AUTO = "hibernate.hbm2ddl.auto";
    protected static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	
	@Resource
	private Environment env;
    
	@Bean
    public abstract LocalContainerEntityManagerFactoryBean entityManagerFactory();

    protected abstract String getConfigurationType();
    
    protected String getProperty(String key) {
    	
    	String placeholder = getConfigurationType().length() == 0 ? "" : getConfigurationType() + ".";
    	try {
			return env.getRequiredProperty( placeholder + key);
		} catch (IllegalStateException e) {
			throw new IllegalArgumentException("Missing property: " + placeholder + key);
		}
    }
    
	@Bean
	protected DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(getProperty(DATABASE_DRIVER));
		dataSource.setUrl(getProperty(DATABASE_URL));
		dataSource.setUsername(getProperty(DATABASE_USERNAME));
		dataSource.setPassword(getProperty(DATABASE_PASSWORD));
		
		return dataSource;
	}
    
    @Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
    
}
