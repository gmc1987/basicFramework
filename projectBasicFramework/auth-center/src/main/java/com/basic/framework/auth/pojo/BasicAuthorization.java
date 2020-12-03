/**
 * 
 */
package com.basic.framework.auth.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author gmc
 *
 */
@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="basic_authorization", indexes= {@Index(columnList="id", unique=true)})
public class BasicAuthorization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private Long authorizationId;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade= {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="roleId", nullable=false)
	private BasicRole roleId;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade= {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="menuId", nullable=false)
	private BasicMenus menuId;
	
	@Column(name="operations", length=225, nullable=true)
	private String operations;

	public Long getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(Long authorizationId) {
		this.authorizationId = authorizationId;
	}

	public BasicRole getRoleId() {
		return roleId;
	}

	public void setRoleId(BasicRole role) {
		this.roleId = role;
	}

	public BasicMenus getMenuId() {
		return menuId;
	}

	public void setMenuId(BasicMenus menu) {
		this.menuId = menu;
	}

	public String getOperations() {
		return operations;
	}

	public void setOperations(String operations) {
		this.operations = operations;
	}

}
