/**
 * 
 */
package com.basic.netty.message.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author gmc
 *
 */

@Configuration
@EnableJpaRepositories("com.basic.netty.message.*.*")
@EnableTransactionManagement
@EnableAutoConfiguration
public class HibernateSessionConfig {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
    private Environment env;
	
	
	@Bean("entityManagerFactory")
	@Primary
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        //此处com.example.*.model是你的java bean所在的包名
        factory.setPackagesToScan("com.basic.netty.message.*.pojo");
        factory.setDataSource(dataSource);

        Map<String, Object> jpaProperties = new HashMap<String, Object>();
        jpaProperties.put("spring.jpa.database", env.getProperty("spring.jpa.database"));
        jpaProperties.put("spring.jpa.open-in-view", env.getProperty("spring.jpa.open-in-view"));
        jpaProperties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        jpaProperties.put("hibernate.current_session_context_class", env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
//        jpaProperties.put("spring.jpa.properties.hibernate.enable_lazy_load_no_trans", env.getProperty("spring.jpa.properties.hibernate.enable_lazy_load_no_trans"));
        
        /*----------------hibernate-redis二级缓存配置（单机模式） start-----------------*/
//        jpaProperties.put("hibernate.cache.use_second_level_cache", env.getProperty("spring.jpa.properties.hibernate.cache.use_second_level_cache"));
//        jpaProperties.put("hibernate.cache.use_query_cache", env.getProperty("spring.jpa.properties.hibernate.cache.use_query_cache"));
//        jpaProperties.put("hibernate.cache.region.factory_class", env.getProperty("spring.jpa.properties.hibernate.cache.region.factory_class"));
//        jpaProperties.put("hibernate.cache.provider_configuration_file_resource_path", env.getProperty("spring.jpa.properties.redisson-config"));
//        jpaProperties.put("hibernate.generate_statistics", env.getProperty("spring.jpa.properties.hibernate.generate_statistics"));
//        jpaProperties.put("hibernate.cache.region_prefix", env.getProperty("spring.jpa.properties.hibernate.cache.region_prefix"));
//        jpaProperties.put("hibernate.cache.use_structured_entries", env.getProperty("spring.jpa.properties.hibernate.cache.use_structured_entries"));
        /*----------------hibernate-redis二级缓存配置（单机模式） end-----------------*/
        
        factory.setJpaPropertyMap(jpaProperties);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

//    @Bean
//    @Autowired
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory());
//        return txManager;
//    }
    
	/**
	 * hibernate事务管理
	 * @param sessionFactory
	 * @return
	 */
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
    	HibernateTransactionManager txManager = new HibernateTransactionManager();
    	txManager.setDataSource(dataSource);
    	txManager.setSessionFactory(sessionFactory);
		return txManager;
    }
    
    /**
     * hibernate sessionFactory bean
     * @param emf
     * @return
     */
    @Bean("sessionFactory")
    public SessionFactory sessionFactory(@Qualifier("entityManagerFactory") EntityManagerFactory emf){
    	SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
        return sessionFactory;
    }
    
    /**
     * hibernate延迟加载
     * @param sessionFactory
     * @return
     */
    @Bean
    public FilterRegistrationBean<OpenSessionInViewFilter> registerOpenEntityManagerInViewFilterBean() {
        FilterRegistrationBean<OpenSessionInViewFilter> registrationBean = new FilterRegistrationBean<OpenSessionInViewFilter>();
        OpenSessionInViewFilter filter = new OpenSessionInViewFilter();
        filter.setSessionFactoryBeanName("sessionFactory");
        registrationBean.setFilter(filter);
        registrationBean.setOrder(0);
        return registrationBean;
    }
    
    @Bean
    public OpenSessionInViewInterceptor openSessionInViewInterceptor(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        OpenSessionInViewInterceptor interceptor = new OpenSessionInViewInterceptor();
        interceptor.setSessionFactory(sessionFactory);
        return interceptor;
    }
    
}
