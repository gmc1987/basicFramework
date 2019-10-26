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
 * @see 用户角色关系表
 */

@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="basic_user_role", indexes= {@Index(columnList="user_role_id", unique=true),
											@Index(columnList="user_id", unique=false),
											@Index(columnList="role_id", unique=false)})
public class BasicUserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_role_id", nullable=false)
	private Long userRoleId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private BasicUser user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id", nullable=false)
	private BasicRole role;
	
	public BasicUser getUser() {
		return user;
	}

	public void setUser(BasicUser user) {
		this.user = user;
	}

	public BasicRole getRole() {
		return role;
	}

	public void setRole(BasicRole role) {
		this.role = role;
	}

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

}
