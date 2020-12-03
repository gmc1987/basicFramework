/**
 * 
 */
package com.basic.framework.platform.basic.dto;

import java.io.Serializable;

import com.basic.framework.platform.authorization.pojo.Authorization;
import com.basic.framework.platform.role.pojo.Role;
import com.basic.framework.platform.users.pojo.PlatformUser;

/**
 * @author gmc
 * @see 用户对象
 */
@SuppressWarnings("serial")
public class UserInfo implements Serializable {
	
	private PlatformUser user;
	
	private Role role;
	
	private Authorization authorization;

	public PlatformUser getUser() {
		return user;
	}

	public void setUser(PlatformUser user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Authorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

}
