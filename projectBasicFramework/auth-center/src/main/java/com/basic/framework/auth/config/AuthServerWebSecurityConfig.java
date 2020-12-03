/**
 * 
 */
package com.basic.framework.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.basic.framework.auth.authorization.passwordEncoder.MyPasswordEncoder;
//import com.basic.framework.auth.authorization.passwordEncoder.MyPasswordEncoder;
import com.basic.framework.auth.authorization.provider.MyDaoAuthenticationProvider;
import com.basic.framework.auth.authorization.service.UserDetailsServiceImpl;

/**
 * @author gmc
 *
 */
@Configuration
@EnableWebSecurity
public class AuthServerWebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private MyPasswordEncoder encoder;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/auth-center/**").permitAll()
		.anyRequest()
		.authenticated();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		MyDaoAuthenticationProvider provider = new MyDaoAuthenticationProvider(userDetailsService, encoder);
		auth.authenticationProvider(provider);
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder();
//	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
}

