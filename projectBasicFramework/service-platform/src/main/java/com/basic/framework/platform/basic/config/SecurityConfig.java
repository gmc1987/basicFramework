///**
// * 
// */
//package com.side.basic.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
//import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//
////import com.side.basic.common.security.SideDaoAuthenticationProvider;
////import com.side.basic.common.security.SideUserServiceDetails;
//
///**
// * @author gmc
// * @see spring security个性化配置
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	@Qualifier("dataSource")
//	private DataSource dataSource;
//
//	@Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey("1q2w3e4rasdf");
//        return converter;
//    }
//	
//    @Bean("jdbcTokenStore")
//    public JdbcTokenStore getJdbcTokenStore() {
//        return new JdbcTokenStore(dataSource);
//    }
//
//    @Bean 
//    public ResourceServerTokenServices tokenService() {
////    	UserInfoTokenServices userInfoTokenService = new UserInfoTokenServices(null, null);
////    	userInfoTokenService.set
//    	DefaultTokenServices defaultTokenServices = null;
//    	try {
//    		defaultTokenServices = new DefaultTokenServices();
//            defaultTokenServices.setTokenStore(getJdbcTokenStore());
//            defaultTokenServices.setSupportRefreshToken(true);
//            defaultTokenServices.setTokenEnhancer(accessTokenConverter());
//            defaultTokenServices.setAuthenticationManager(authenticationManager());
//    	} catch(Exception e) {
//    		e.printStackTrace();
//    	}
//        return defaultTokenServices;
//    }
//    
//    
//    @Bean("authenticationManagerBean")
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//    
////    @Override
////    public AuthenticationManager authenticationManager() throws Exception {
////        OAuth2AuthenticationManager authManager = new OAuth2AuthenticationManager();
////        authManager.setTokenServices(tokenService());
////        return authManager;
////    }
//	
//}
