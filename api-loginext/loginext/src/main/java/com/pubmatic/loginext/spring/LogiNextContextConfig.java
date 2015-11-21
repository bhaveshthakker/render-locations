package com.pubmatic.loginext.spring;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * 
 * @author Bhavesh 
 * A spring application context loading class.
 *
 */
@Configuration
@PropertySource("classpath:loginext.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.pubmatic.loginext.dao.repository" })
@ComponentScan(basePackages={"com.pubmatic.loginext"})
@EnableAsync
public class LogiNextContextConfig {

	public LogiNextContextConfig() {
		super();
	}

	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String userName;
	
	@Value("${jdbc.password}")
	private String password;
	
	@Value("${jpa.generateDdl}")
	boolean jpaGenerateDdl;

	// Hibernate specific
	@Value("${hibernate.dialect}")
	String hibernateDialect;
	
	@Value("${hibernate.show_sql}")
	boolean hibernateShowSql;
	
	@Value("${hibernate.hbm2ddl.auto}")
	String hibernateHbm2ddlAuto;
	
	@Value("${hibernate.format_sql}")
	String formatSql;
	
	@Value("${hibernate.ejb.naming_strategy}")
	String namingStrategy;
	
	@Value("${scanner.flash.content.type}")
	public String flashContentType;

	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties(){
	  PropertySourcesPlaceholderConfigurer pspc =
	    new PropertySourcesPlaceholderConfigurer();
	  Resource[] resources = new ClassPathResource[ ]
	    { new ClassPathResource( "loginext.properties" ) };
	  pspc.setLocations( resources );
	  pspc.setIgnoreUnresolvablePlaceholders( true );
	  return pspc;
	}
	
	@Bean
	DataSource dataSource(Environment env) {

		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource, Environment env) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean
				.setPackagesToScan(new String[] { "com.pubmatic.loginext.bean" });

		final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter() {
			{
				// setDatabase( Database.H2 ); // TODO: is this necessary
				setDatabasePlatform(hibernateDialect);
				setShowSql(hibernateShowSql);
				setGenerateDdl(jpaGenerateDdl);
			}
		};

		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactoryBean.setJpaProperties(additionlProperties());
		return entityManagerFactoryBean;
	}	

	@Bean
	JpaTransactionManager transactionManager(
			EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	
	final Properties additionlProperties() {
		return new Properties() {
			{
				// use this to inject additional properties in the EntityManager
				setProperty("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
				setProperty("hibernate.format_sql", formatSql);
				setProperty("hibernate.ejb.naming_strategy", namingStrategy);
			}
		};
	}	
}
