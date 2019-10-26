/**
 * 
 */
package com.basic.framework.auth.authorization.provider;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.basic.framework.auth.authorization.service.UserDetailsServiceImpl;

/**
 * @author gmc
 *
 */
public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {

	public MyDaoAuthenticationProvider() {}
	
	public MyDaoAuthenticationProvider(UserDetailsServiceImpl userServiceDetails, PasswordEncoder passwordEncode) {
		setUserDetailsService(userServiceDetails);
		setPasswordEncoder(passwordEncode);
	}
}
