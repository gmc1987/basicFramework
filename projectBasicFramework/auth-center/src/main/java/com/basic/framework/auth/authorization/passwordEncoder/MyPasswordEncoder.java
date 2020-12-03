/**
 * 
 */
package com.basic.framework.auth.authorization.passwordEncoder;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.basic.framework.auth.common.UtilRSA;


/**
 * @author gmc
 * 
 */
@Component
public class MyPasswordEncoder extends BCryptPasswordEncoder {

	//重写父类方法
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		try {
			String pw = UtilRSA.decryptByPrivateKey(
					Base64.decodeBase64(rawPassword.toString()), UtilRSA.THIS_PRIVATEKEY);
			return super.matches(pw, encodedPassword);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
