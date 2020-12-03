/**
 * 
 */
package com.basic.framework.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author gmc
 *
 */
public class DycTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pwd = encoder.encode("admin");
		System.out.println(pwd);
		System.out.println(encoder.matches("admin", pwd));
	}

}
