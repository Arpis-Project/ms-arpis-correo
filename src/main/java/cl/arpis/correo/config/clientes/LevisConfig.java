package cl.arpis.correo.config.clientes;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
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
@Profile("levis")
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
		transactionManagerRef = "transacctionManager",
		basePackages = "cl.arpis.correo.persistence.clientes.levis.repositories")
public class LevisConfig {

	@Autowired
	private Environment environment;

	@Primary
	@Bean(name = "dataSource")
	public DataSource oracleDataSouce() {
		DriverManagerDataSource  dataSource = new DriverManagerDataSource();
		dataSource.setUrl(environment.getProperty("spring.oracle.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.oracle.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.oracle.datasource.password"));
		dataSource.setDriverClassName(environment.getProperty("spring.oracle.datasource.driver-class-name"));
		return dataSource;
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(oracleDataSouce());
		entityManager.setPackagesToScan("cl.arpis.correo.persistence.clientes.levis.entities");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManager.setJpaVendorAdapter(vendorAdapter);
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.oracle.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.show-sql", environment.getProperty("spring.oracle.jpa.show"));
		properties.put("hibernate.dialect", environment.getProperty("spring.oracle.jpa.database-platform"));
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

	@Primary
	@Bean(name = "transacctionManager")
	public PlatformTransactionManager oracleTransacctionManager() {
		JpaTransactionManager transacctionManager = new JpaTransactionManager();
		transacctionManager.setEntityManagerFactory(oracleEntityManagerFactory().getObject());
		return transacctionManager;
	}

}
