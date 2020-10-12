package com.practice.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.practice" })
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {
	
	@Autowired
	private Environment env;

	// Bean -> creating view resolver to display jsp's
	@Bean
	public InternalResourceViewResolver viewResolver() {

		return new InternalResourceViewResolver() {
			{
				setContentType("text/html; charset=ISO-8859-1");
				setViewClass(JstlView.class);
				setPrefix("/WEB-INF/jsps/");
				setSuffix(".jsp");
			}
		};
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
	}

	/* Database configuration */
	@Bean(name = "mySqlDataSource")
	public DataSource mySqldataSource() {

		return new DriverManagerDataSource() {
			{
				setDriverClassName(env.getRequiredProperty("spring.mysql.datasource.driver-class-name"));
				setUrl(env.getRequiredProperty("spring.mysql.datasource.jdbc-url"));
				setUsername(env.getRequiredProperty("spring.mysql.datasource.username"));
				setPassword(env.getRequiredProperty("spring.mysql.datasource.password"));
			}
		};
	}

	/*
	 * Spring-JAP(included hibernate) : Entity Manager Factory object for data
	 * persistence
	 */
	@Bean(name = "mysqlEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			@Qualifier("mySqlDataSource") DataSource mySqlDataSource,
			@Qualifier("mySqlJpaVendorAdapter") JpaVendorAdapter mySqlJpaVendorAdapter) {

		return new LocalContainerEntityManagerFactoryBean() {
			{
				setPersistenceUnitName("mySql-unit");
				setDataSource(mySqlDataSource);
				setPackagesToScan(new String[] { "com.practice.bean" });
				setJpaVendorAdapter(mySqlJpaVendorAdapter);
				setJpaProperties(hibernateProperties());
			}
		};
	}

	/* Hibernate configuration */
	Properties hibernateProperties() {
		return new Properties() {
			private static final long serialVersionUID = 1L;

			{
				setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("spring.mysql.hibernate.hbm2ddl.auto"));
				setProperty("hibernate.show_sql", env.getRequiredProperty("spring.mysql.hibernate.show_sql"));
			}
		};
	}

	@Bean(name = "mySqlTransactionManager")
	public PlatformTransactionManager mysqlTransactionManager(
			@Qualifier("mysqlEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Bean(name = "mySqlJpaVendorAdapter")
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter() {
			{
				setDatabase(Database.MYSQL);
				setShowSql(true);
				setGenerateDdl(true);
				setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
			}
		};
	}

}
