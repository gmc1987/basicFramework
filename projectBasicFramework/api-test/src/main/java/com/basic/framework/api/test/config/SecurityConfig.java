///**
// * 
// */
//package com.basic.framework.api.test.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
///**
// * @author gmc
// * @see 资源服务器oauth2配置
// */
//public class SecurityConfig {
//
//	  @Bean
//	  SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
//	    http.authorizeExchange()
//	        .pathMatchers("/test/**").authenticated()
//	        .anyExchange().authenticated()
//	        .and()
//	        .oauth2ResourceServer()
//	        .jwt();
//	    return http.build();
//	  }
//}
