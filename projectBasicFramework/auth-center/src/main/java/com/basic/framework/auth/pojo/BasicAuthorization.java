/**
 * 
 */
package com.basic.framework.auth.pojo;

import java.io.Serializable;

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
@Table(name="basic_authorization", indexes= {@Index(columnList="authorization_id", unique=true)})
public class BasicAuthorization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="authorization_id", nullable=false)
	private Long authorizationId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id")
	private BasicRole role;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="menu_id")
	private BasicMenus menu;
	
	@Column(name="operations", length=225, nullable=true)
	private String operations;

	public Long getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(Long authorizationId) {
		this.authorizationId = authorizationId;
	}

	public BasicRole getRole() {
		return role;
	}

	public void setRole(BasicRole role) {
		this.role = role;
	}

	public BasicMenus getMenu() {
		return menu;
	}

	public void setMenu(BasicMenus menu) {
		this.menu = menu;
	}

	public String getOperations() {
		return operations;
	}

	public void setOperations(String operations) {
		this.operations = operations;
	}

}
