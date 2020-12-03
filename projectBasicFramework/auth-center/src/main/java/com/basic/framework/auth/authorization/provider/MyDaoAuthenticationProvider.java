/**
 * 
 */
package com.basic.framework.auth.authorization.provider;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import com.basic.framework.auth.authorization.passwordEncoder.MyPasswordEncoder;
import com.basic.framework.auth.authorization.service.UserDetailsServiceImpl;

/**
 * @author gmc
 *
 */
public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {

	public MyDaoAuthenticationProvider() {}
	
	public MyDaoAuthenticationProvider(UserDetailsServiceImpl userServiceDetails, MyPasswordEncoder passwordEncode) {
		setUserDetailsService(userServiceDetails);
		setPasswordEncoder(passwordEncode);
	}
}
