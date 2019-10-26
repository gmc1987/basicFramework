/**
 * 
 */
package com.basic.framework.auth.authorization.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.basic.framework.auth.pojo.BasicAccount;
import com.basic.framework.auth.pojo.BasicAuthorization;
import com.basic.framework.auth.pojo.BasicMenus;
import com.basic.framework.auth.pojo.BasicRole;
import com.basic.framework.auth.pojo.BasicUser;
import com.basic.framework.auth.pojo.BasicUserRole;

/**
 * @author gmc
 *
 */
public class AuthUserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BasicUser basicUser;

	private BasicRole basicRole;

	private BasicAccount basicAccount;

	private Map<String, List<BasicMenus>> menus;

	private List<BasicUserRole> userRoles;

	private Map<String, List<BasicAuthorization>> basicAuthorizations;

	public AuthUserEntity() {
	}

	public AuthUserEntity(BasicUser basicUser, BasicRole basicRole, BasicAccount basicAccount, Map<String, List<BasicMenus>> menus,
			List<BasicUserRole> userRoles, Map<String, List<BasicAuthorization>> basicAuthorizations) {
		this.basicUser = basicUser;
		this.basicRole = basicRole;
		this.basicAccount = basicAccount;
		this.menus = menus;
		this.userRoles = userRoles;
		this.basicAuthorizations = basicAuthorizations;
	}

	public BasicUser getBasicUser() {
		return basicUser;
	}

	public void setBasicUser(BasicUser basicUser) {
		this.basicUser = basicUser;
	}

	public BasicRole getBasicRole() {
		return basicRole;
	}

	public void setBasicRole(BasicRole basicRole) {
		this.basicRole = basicRole;
	}

	public BasicAccount getBasicAccount() {
		return basicAccount;
	}

	public void setBasicAccount(BasicAccount basicAccount) {
		this.basicAccount = basicAccount;
	}

	public Map<String, List<BasicMenus>> getMenus() {
		return menus;
	}

	public void setMenus(Map<String, List<BasicMenus>> menus) {
		this.menus = menus;
	}

	public List<BasicUserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<BasicUserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Map<String, List<BasicAuthorization>> getBasicAuthorizations() {
		return basicAuthorizations;
	}

	public void setBasicAuthorizations(Map<String, List<BasicAuthorization>> basicAuthorizations) {
		this.basicAuthorizations = basicAuthorizations;
	}

}
