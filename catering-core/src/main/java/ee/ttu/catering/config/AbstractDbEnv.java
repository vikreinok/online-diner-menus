package ee.ttu.catering.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.mongodb.DB;

import ee.ttu.catering.cloudservice.MongoDbConnctor;
import ee.ttu.catering.rest.model.Diner;

public abstract class AbstractDbEnv extends ApplicationConfig {

	protected static String TYPE = "test.";
	
	protected static final String DATABASE_DRIVER = "jdbc.driver";
	protected static final String DATABASE_PASSWORD = "jdbc.password";
	protected static final String DATABASE_URL = "jdbc.url";
	protected static final String DATABASE_USERNAME = "jdbc.username";

	protected static final String HIBERNATE_DIALECT = "hibernate.dialect";
    protected static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    protected static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    protected static final String HIBERNATE_AUTO = "hibernate.hbm2ddl.auto";
	
    protected final String[] packagesToScan = new String[]{Diner.class.getPackage().getName()};

    
	@Resource
	private Environment env;
    
	@Bean
    public abstract LocalContainerEntityManagerFactoryBean entityManagerFactory();

    protected abstract String getConfigurationType();
    
	@Bean
	protected DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(20);
        dataSource.setMaxIdle(10);
        
		dataSource.setConnectionProperties("useUnicode=true;characterEncoding=UTF-8");	
        
		dataSource.setDriverClassName(getProperty(DATABASE_DRIVER));
		dataSource.setUrl(getProperty(DATABASE_URL));
		dataSource.setUsername(getProperty(DATABASE_USERNAME));
		dataSource.setPassword(getProperty(DATABASE_PASSWORD));
		
		return dataSource;
	}
    
	@Bean
	public DB initMongoDb() {
		return new MongoDbConnctor().connection();
	}
	  
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
	protected String getProperty(String key) {
		String placeholder = getConfigurationType().length() == 0 ? "" : getConfigurationType() + ".";
		try {
			return env.getRequiredProperty(placeholder + key);
		} catch (IllegalStateException e) {
			throw new IllegalArgumentException("Missing property: " + placeholder + key);
		}
	}
}
