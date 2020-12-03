/**
 * 
 */
package com.basic.framework.platform.authorization.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.basic.framework.platform.menus.pojo.Menus;
import com.basic.framework.platform.role.pojo.Role;

//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author gmc
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="basic_authorization")
@DynamicUpdate(true)
@JsonIgnoreProperties(value={"roleId","menuId","hibernateLazyInitializer","handler","fieldHandler"})
public class Authorization implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer authorizationId;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade= {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "roleId")
	private Role roleId;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "menuId")
	private Menus menuId;
	
	@Column(name="operations", length=100, nullable=true)
	private String operationList;

	public Integer getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(Integer authorizationId) {
		this.authorizationId = authorizationId;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}

	public Menus getMenuId() {
		return menuId;
	}

	public void setMenuId(Menus menuId) {
		this.menuId = menuId;
	}

	public String getOperationList() {
		return operationList;
	}

	public void setOperationList(String operationList) {
		this.operationList = operationList;
	}
	
}
