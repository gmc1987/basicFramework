///**
// * 
// */
//package com.side.basic.config;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//
///**
// * @author gmc
// *
// */
//@Configuration
//@EnableResourceServer
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SideResourceServerConfig extends ResourceServerConfigurerAdapter{
//
//	@Autowired
//    private ResourceServerTokenServices tokenServices;
//
//    @Value("${security.oauth2.resource.id}")
//    private String resourceIds;
//    
//    @Qualifier("authenticationManagerBean")
//	@Autowired
//    private AuthenticationManager authenticationManagerBean;
//    
//    @Qualifier("jdbcTokenStore")
//    @Autowired
//    private JdbcTokenStore jdbcTokenStroe;
//	
//	
//	@Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.resourceId(resourceIds)
//        		 .tokenStore(jdbcTokenStroe)
//        		 .tokenServices(tokenServices);
//    }
//	
//	@Override
//    public void configure(HttpSecurity http) throws Exception {
//		 http.csrf().disable().exceptionHandling()
//         .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//         .and()
//         .authorizeRequests()
//         .anyRequest().authenticated()
//         .and()
//         .httpBasic();
//    }
//
//}
