/**
 * 
 */
package com.basic.framework.platform.basic.common.security;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;

import com.basic.framework.platform.users.pojo.PlatformUser;

/**
 * @author gmc
 *
 */
public class MyUserDetails extends User {
	
	
	private PlatformUser user;

	@SuppressWarnings("unchecked")
	public MyUserDetails(PlatformUser user) {
		super(user.getUserCode(), user.getAccount().getAccPassword(), true, true, true, true, Collections.EMPTY_SET);
	     this.user = user;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlatformUser getUser() {
		return user;
	}

	public void setUser(PlatformUser user) {
		this.user = user;
	}

}
