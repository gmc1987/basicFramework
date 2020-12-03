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
public class AuthorizationDto implements Serializable{
	
	public AuthorizationDto() {
		
	}
	
	public AuthorizationDto(Integer authorizationId, Integer roleId, Integer menuId, String operationList, String key) {
		this.authorizationId = authorizationId;
		this.roleId = roleId;
		this.menuId = menuId;
		this.operationList = operationList;
		this.key = key;
	}

	private Integer authorizationId;
	
	private Integer roleId;
	
	private Integer menuId;
	
	private String operationList;
	
	private String key;

	public Integer getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(Integer authorizationId) {
		this.authorizationId = authorizationId;
	}

	public String getOperationList() {
		return operationList;
	}

	public void setOperationList(String operationList) {
		this.operationList = operationList;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
