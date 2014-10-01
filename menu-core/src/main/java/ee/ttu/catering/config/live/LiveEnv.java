package ee.ttu.catering.config.live;

import java.util.Properties;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import ee.ttu.catering.config.AbstractDbEnv;


@PropertySource("classpath:live_db.properties")
public class LiveEnv extends AbstractDbEnv {
	
	
	@Bean
	@Override
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(getProperty(ENTITYMANAGER_PACKAGES_TO_SCAN));
				
		entityManagerFactoryBean.setJpaProperties(hibernateProperties());
		
		return entityManagerFactoryBean;
	}
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put(HIBERNATE_DIALECT, getProperty(HIBERNATE_DIALECT));
		properties.put(HIBERNATE_SHOW_SQL, getProperty(HIBERNATE_SHOW_SQL));
        properties.put(HIBERNATE_AUTO, getProperty(HIBERNATE_AUTO));
		return properties;	
	}

	@Override
	protected String getConfigurationType() {
		return "";
	}
	
}
