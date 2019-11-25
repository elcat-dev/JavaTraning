package com.ertc.taskman;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.ertc.taskman"})
public class TaskManConfig {
    @Bean
    public LocalSessionFactoryBean getFactory(){
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(getDataSource());
        factory.setPackagesToScan(new String[]{"com.ertc.taskman"});
        factory.setHibernateProperties(hiberProperties());
        return factory;
    }

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@desktop-779t2cr:1521:xe");
        dataSource.setUsername("taskman");
        dataSource.setPassword("taskman");
        return dataSource;
    }

    private Properties hiberProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle12cDialect");
        properties.setProperty("hibernate.show_sql","false");
        properties.setProperty("hibernate.current_session_context_class","thread");
        properties.setProperty("hibernate.connection.pool_size","8");
        return properties;
    }
}
