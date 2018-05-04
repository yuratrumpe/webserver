package com.yuratrumpe.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.yuratrumpe.dao", "com.yuratrumpe.services", "com.yuratrumpe.processor"})
@PropertySources({
        @PropertySource(value = "classpath:jdbc.properties"),
        @PropertySource(value = "classpath:hibernate.properties")
})
@EnableTransactionManagement
public class AppConfig {


    @Autowired
    private Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Bean (destroyMethod = "close")
    public DataSource dataSource() {
        BasicDataSource basicDataSource =  new BasicDataSource();
        basicDataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        basicDataSource.setUrl(environment.getProperty("jdbc.url"));
        basicDataSource.setUsername(environment.getProperty("jdbc.username"));
        basicDataSource.setPassword(environment.getProperty("jdbc.password"));
        basicDataSource.setConnectionProperties(
                "characterEncoding=" + environment.getProperty("characterEncoding") +
                ";serverTimezone=" + environment.getProperty("serverTimezone") +
                ";useSSL=" + environment.getProperty("useSSL") +
                ";verifyServerCertificate=" + environment.getProperty("verifyServerCertificate"));
        return basicDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public Properties hibernateProperties() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("hibernate.properties"));
        Properties properties = new Properties();
        try {
            properties = bean.getObject();
            return properties;
        } catch (IOException e) {
            return properties;
        }
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setPackagesToScan("com.yuratrumpe.model");

        return sessionFactory;

    }


}
