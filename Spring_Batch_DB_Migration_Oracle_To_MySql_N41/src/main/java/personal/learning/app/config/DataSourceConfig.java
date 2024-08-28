package personal.learning.app.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataSourceConfig {
	
	// Oracle DB
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource datasource() {
		return DataSourceBuilder.create().build();
	}
	
	// Oracle DB
	@Bean
	@ConfigurationProperties(prefix = "spring.practice.datasource")
	public DataSource practiceDatasource() {
		return DataSourceBuilder.create().build();
	}
	
	// MySql DB
	@Bean
	@ConfigurationProperties(prefix = "spring.university.datasource")
	public DataSource universityDatasource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "practiceEntityManagerFactory")
	public EntityManagerFactory practiceEntityManagerFactory() {
		
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = 
				new LocalContainerEntityManagerFactoryBean();
		
		localContainerEntityManagerFactoryBean.setDataSource(practiceDatasource());
		localContainerEntityManagerFactoryBean.setPackagesToScan("personal.learning.app.oracle.entity");
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		localContainerEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		localContainerEntityManagerFactoryBean.afterPropertiesSet();
		
		return localContainerEntityManagerFactoryBean.getObject();
	}
	
	@Bean(name = "universityEntityManagerFactory")
	@Primary
	public EntityManagerFactory universityEntityManagerFactory() {
		
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = 
				new LocalContainerEntityManagerFactoryBean();
		
		localContainerEntityManagerFactoryBean.setDataSource(universityDatasource());
		localContainerEntityManagerFactoryBean.setPackagesToScan("personal.learning.app.mysql.entity");
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		localContainerEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		localContainerEntityManagerFactoryBean.afterPropertiesSet();
		
		return localContainerEntityManagerFactoryBean.getObject();
	}
	
	@Bean
	@Primary
	public JpaTransactionManager jpaTransactionManager() {
		
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		
		jpaTransactionManager.setDataSource(universityDatasource());
		jpaTransactionManager.setEntityManagerFactory(universityEntityManagerFactory());
		
		return jpaTransactionManager;
	}
}
