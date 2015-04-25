package ee.ttu.catering.config.live;

import java.util.Properties;

import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import ee.ttu.catering.config.AbstractDbEnv;
import ee.ttu.catering.config.dialect.IsolationSupportHibernateJpaDialect;

@Configuration
@PropertySource("classpath:live_db.properties")
public class LiveEnv extends AbstractDbEnv {
	
	@Override
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		
		entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
		entityManagerFactory.setPackagesToScan(packagesToScan);
		entityManagerFactory.setJpaDialect(new IsolationSupportHibernateJpaDialect());
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setJpaProperties(jpaProperties());
		
		return entityManagerFactory;
	}
	
	private Properties jpaProperties() {
		Properties props = new Properties();
		
		props.put(Environment.DIALECT, getProperty(HIBERNATE_DIALECT));
		props.put(Environment.HBM2DDL_AUTO, getProperty(HIBERNATE_AUTO));
		props.put(Environment.SHOW_SQL, getProperty(HIBERNATE_SHOW_SQL));
		props.put(Environment.FORMAT_SQL, getProperty(HIBERNATE_FORMAT_SQL));
//		props.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);
		
		return props;
	}

	@Override
	protected String getConfigurationType() {
		return "";
	}
	
}
