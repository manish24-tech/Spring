package com.practice.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
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

	
	/* (non-Javadoc)
	 * responsible to serve static resources such as images, js, and, css files under web-app directory
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
	}
	
	/* Database configuration */
	@Bean(name="mySqlDataSource")
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

	/* Spring-Hibernate : Session Factory object for data persistence */
	/**
	 * LocalSessionFactoryBean is class of org.springframework.orm.hibernate5
	 * responsible to create session factory object for data persistence which is replacement of hibernate.cfg.xml file
	 * it mainly set data-source, packages of entities for mapping and hibernate properties for ddl manipulation
	 * session factory object is injected/bind with HibernateTransactionManager and HibernateTemplate bean to initiate session
	 * it create one session per one transaction like save, delete and update..etc
	 *  
	 * @param mySqldataSource
	 * @return
	 */
	@Bean(name="mySqlsessionFactory")
	public LocalSessionFactoryBean sessionFactory(@Qualifier("mySqlDataSource") DataSource mySqldataSource) {
		
		return new LocalSessionFactoryBean() {
			{
				setDataSource(mySqldataSource);
				setPackagesToScan(new String[] { "com.practice.bean" });
				setHibernateProperties(hibernateProperties());
			}
		};
	}

	

	/* Hibernate configuration */
	Properties hibernateProperties() {
		return new Properties() {
			private static final long serialVersionUID = 1L;
			{
				setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("spring.mysql.hibernate.hbm2ddl.auto"));
				setProperty("hibernate.dialect", env.getRequiredProperty("spring.mysql.hibernate.dialect"));
				setProperty("hibernate.show_sql", env.getRequiredProperty("spring.mysql.hibernate.show_sql"));
			}
		};
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(@Qualifier("mySqlsessionFactory") LocalSessionFactoryBean localSessionFactoryBean) {
		//HibernateTransactionManager htm = new HibernateTransactionManager();
		//htm.setSessionFactory(localSessionFactoryBean.getObject());
		return new HibernateTransactionManager() {
			private static final long serialVersionUID = 1L;
			{
		
				setSessionFactory(localSessionFactoryBean.getObject());
			}
		};
	}
	 
	@Bean
	public HibernateTemplate hibernateTemplate(@Qualifier("mySqlsessionFactory") LocalSessionFactoryBean localSessionFactoryBean) {
		return new HibernateTemplate(localSessionFactoryBean.getObject());
	}
	
	
	

}
