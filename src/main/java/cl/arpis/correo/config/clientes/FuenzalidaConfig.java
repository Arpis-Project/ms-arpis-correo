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
@Profile("fuenzalida")
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
		transactionManagerRef = "transacctionManager",
		basePackages = "cl.arpis.correo.persistence.clientes.fuenzalida.repositories")
public class FuenzalidaConfig {

	@Autowired
	private Environment environment;

	@Primary
	@Bean(name = "dataSource")
	public DataSource postgreDataSouce() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(environment.getProperty("spring.postgre.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.postgre.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.postgre.datasource.password"));
		dataSource.setDriverClassName(environment.getProperty("spring.postgre.datasource.driver-class-name"));
		return dataSource;
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean postgreEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(postgreDataSouce());
		entityManager.setPackagesToScan("cl.arpis.correo.persistence.clientes.fuenzalida.entities");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManager.setJpaVendorAdapter(vendorAdapter);
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.postgre.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.show-sql", environment.getProperty("spring.postgre.jpa.show"));
		properties.put("hibernate.dialect", environment.getProperty("spring.postgre.jpa.database-platform"));
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

	@Primary
	@Bean(name = "transacctionManager")
	public PlatformTransactionManager postgreTransacctionManager() {
		JpaTransactionManager transacctionManager = new JpaTransactionManager();
		transacctionManager.setEntityManagerFactory(postgreEntityManagerFactory().getObject());
		return transacctionManager;
	}

}
