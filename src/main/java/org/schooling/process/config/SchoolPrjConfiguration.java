package org.schooling.process.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class SchoolPrjConfiguration {

	@Value("${db.driver}")
	private String DB_DRIVER;

	@Value("${db.password}")
	private String DB_PASSWORD;

	@Value("${db.url}")
	private String DB_URL;

	@Value("${db.username}")
	private String DB_USERNAME;

	@Value("${db.show-sql}")
	private String SHOW_SQL;

	@Value("${db.remove-abandoned-timeout}")
	private String REMOVE_ABANDONED;

	@Value("${db.initial-size}")
	private String INTIAL_SIZE;

	@Value("${db.max-active}")
	private String MAX_ACTIVE;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DB_DRIVER);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USERNAME);
		dataSource.setPassword(DB_PASSWORD);
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setPackagesToScan("org.schooling.process.entity");
		sessionFactoryBean.setDataSource(dataSource());
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.show_sql", SHOW_SQL);
		hibernateProperties.setProperty("removeAbandoned", "true");
		hibernateProperties.setProperty("removeAbandonedTimeout", REMOVE_ABANDONED);
		hibernateProperties.setProperty("initialSize", INTIAL_SIZE);
		hibernateProperties.setProperty("maxActive", MAX_ACTIVE);
		sessionFactoryBean.setHibernateProperties(hibernateProperties);

		return sessionFactoryBean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	@Bean
	public HibernateTemplate hibernateTemplate() {
		HibernateTemplate hibernateTemplate = new HibernateTemplate();
		hibernateTemplate.setSessionFactory(sessionFactory().getObject());
		return hibernateTemplate;
	}

}