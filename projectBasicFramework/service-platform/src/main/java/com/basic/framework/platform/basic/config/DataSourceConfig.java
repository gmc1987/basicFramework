/**
 * 
 */
package com.basic.framework.platform.basic.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * @author gmc
 * @see 数据源配置加载类
 */
@Configuration
public class DataSourceConfig {

//	/**
//	 * 默认数据源.
//	 * @return
//	 */
//	@Bean("dataSource")
//	@Primary
//	@ConfigurationProperties(prefix = "spring.datasource.first.hikari")
//	public HikariDataSource dataSource() {
//		return DataSourceBuilder.create().type(HikariDataSource.class).build();
//	}
//	
//	/**
//	 * 其他数据源.
//	 * @return
//	 */
//	@Bean("secondDataSource")
//	@ConfigurationProperties(prefix = "spring.datasource.second.hikari")
//	public HikariDataSource secondDataSource() {
//		return DataSourceBuilder.create().type(HikariDataSource.class).build();
//	}
	
	@Primary
	@Bean("dataSource")
	@ConfigurationProperties("spring.datasource.druid.one")
	public DataSource dataSourceOne(){
	    return DruidDataSourceBuilder.create().build();
	}
	
}
