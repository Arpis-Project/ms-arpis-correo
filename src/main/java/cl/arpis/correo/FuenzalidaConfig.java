package cl.arpis.correo;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySources({@PropertySource("classpath:db-dev.properties")})
@Profile("fuenzalida")
@EnableJpaRepositories(entityManagerFactoryRef = "postgreEntityManagerFactory", transactionManagerRef = "postgreTransacctionManager", basePackages = "cl.arpis.correo.repository.postgre")
public class FuenzalidaConfig {

	@Autowired
	private Environment environment;

	@Bean(name = "postgreDataSouce")
	public DataSource postgreDataSouce() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(environment.getProperty("spring.postgre.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.postgre.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.postgre.datasource.password"));
		dataSource.setDriverClassName(environment.getProperty("spring.postgre.datasource.driver-class-name"));
		return dataSource;
	}

	@Bean(name = "postgreEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean postgreEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(postgreDataSouce());
		entityManager.setPackagesToScan("cl.arpis.correo.entities");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManager.setJpaVendorAdapter(vendorAdapter);
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.postgre.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.shwo-sql", environment.getProperty("spring.postgre.jpa.show"));
		properties.put("hibernate.dialect", environment.getProperty("spring.postgre.jpa.database-platform"));
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

	@Bean(name = "postgreTransacctionManager")
	public PlatformTransactionManager postgreTransacctionManager() {
		JpaTransactionManager transacctionManager = new JpaTransactionManager();
		transacctionManager.setEntityManagerFactory(postgreEntityManagerFactory().getObject());
		return transacctionManager;
	}
}
