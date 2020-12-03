/**
 * 
 */
package com.basic.framework.auth.authorization.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.basic.framework.auth.pojo.Account;
import com.basic.framework.auth.pojo.BasicAuthorization;
import com.basic.framework.auth.pojo.BasicMenus;
import com.basic.framework.auth.pojo.BasicRole;
import com.basic.framework.auth.pojo.BasicUserRole;
import com.basic.framework.auth.pojo.PlatformUser;

/**
 * @author gmc
 *
 */
public class AuthUserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PlatformUser platformUser;

	private BasicRole basicRole;

	private Account account;

	private Map<String, List<BasicMenus>> menus;

	private List<BasicUserRole> userRoles;

	private Map<String, List<BasicAuthorization>> basicAuthorizations;

	public AuthUserEntity() {
	}

	public AuthUserEntity(PlatformUser platformUser, BasicRole basicRole, Account account, Map<String, List<BasicMenus>> menus,
			List<BasicUserRole> userRoles, Map<String, List<BasicAuthorization>> basicAuthorizations) {
		this.platformUser = platformUser;
		this.basicRole = basicRole;
		this.account = account;
		this.menus = menus;
		this.userRoles = userRoles;
		this.basicAuthorizations = basicAuthorizations;
	}

	public PlatformUser getPlatformUser() {
		return platformUser;
	}

	public void setPlatformUser(PlatformUser platformUser) {
		this.platformUser = platformUser;
	}

	public BasicRole getBasicRole() {
		return basicRole;
	}

	public void setBasicRole(BasicRole basicRole) {
		this.basicRole = basicRole;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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
