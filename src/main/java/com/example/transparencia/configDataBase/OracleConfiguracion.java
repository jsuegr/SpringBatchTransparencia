package com.example.transparencia.configDataBase;

import javax.sql.DataSource;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;


@Configuration
@EnableTransactionManagement
public class OracleConfiguracion {

	@Value("${hibernate.dialect}")
	private String DIALECT;

	@Value("${hibernate.show_sql}")
	private String SHOW_SQL;

	@Value("${hibernate.pack.scan.rendicion}")
	private String PACKAGES_TO_SCAN;

	@Value("${spring.datasource.jndi-name}")
	private String primaryJndiName;

	private JndiDataSourceLookup lookup = new JndiDataSourceLookup();

	@Bean
	public DataSource dataSource() {
		return lookup.getDataSource(primaryJndiName);
	}

	// Configuracion para Hibernate

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", DIALECT);
		hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager trxManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan(PACKAGES_TO_SCAN);
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
		Properties additionalProperties = new Properties();
		additionalProperties.put("hibernate.dialect", DIALECT);
		additionalProperties.put("hibernate.show_sql", SHOW_SQL);
		entityManagerFactory.setJpaProperties(additionalProperties);
		return entityManagerFactory;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
		return transactionManager;
	}


	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;

	public String getDIALECT() {
		return DIALECT;
	}

	public void setDIALECT(String dIALECT) {
		DIALECT = dIALECT;
	}

	public String getSHOW_SQL() {
		return SHOW_SQL;
	}

	public void setSHOW_SQL(String sHOW_SQL) {
		SHOW_SQL = sHOW_SQL;
	}

	public String getPACKAGES_TO_SCAN() {
		return PACKAGES_TO_SCAN;
	}

	public void setPACKAGES_TO_SCAN(String pACKAGES_TO_SCAN) {
		PACKAGES_TO_SCAN = pACKAGES_TO_SCAN;
	}

	public String getPrimaryJndiName() {
		return primaryJndiName;
	}

	public void setPrimaryJndiName(String primaryJndiName) {
		this.primaryJndiName = primaryJndiName;
	}
}
