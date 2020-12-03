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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author gmc
 * @see 用户角色关系表
 */

@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
@Entity
@DynamicUpdate
@Table(name="basic_user_role")
public class BasicUserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long userRoleId;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name = "userId")
	private PlatformUser userId;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name = "roleId")
	private BasicRole roleId;
	
	public PlatformUser getUserId() {
		return userId;
	}

	public void setUserId(PlatformUser userId) {
		this.userId = userId;
	}

	public BasicRole getRoleId() {
		return roleId;
	}

	public void setRoleId(BasicRole roleId) {
		this.roleId = roleId;
	}

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

}
