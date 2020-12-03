/**
 * 
 */
package com.basic.framework.platform.authorization.dto;

import java.io.Serializable;

/**
 * @author gmc
 *
 */
@SuppressWarnings("serial")
public class RoleUserDto implements Serializable {

	private Long userRoleId;
	
	private Integer roleId;
	
	private Long userId;

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
