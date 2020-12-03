/**
 * 
 */
package com.basic.framework.platform.role.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.basic.framework.platform.authorization.pojo.Authorization;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author gmc
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
@Entity
@DynamicUpdate
@Table(name="basic_role")
public class Role implements Serializable {
	
	public Role() {}
	
	public Role(String roleCode, String roleName, Integer roleStatus, Date createDate, Long createBy) {
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.roleStatus = roleStatus;
		this.createDate = createDate;
		this.createBy = createBy;
	}
	
	public Role(Integer roleId) {
		this.roleId = roleId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer roleId;
	
	@Column(length=32, nullable=false)
	private String roleCode;
	
	@Column(length=100, nullable=false)
	private String roleName;
	
	@Column(nullable=false)
	private Integer roleStatus;
	
	@Column
	private Date createDate;
	
	@Column(length=32, nullable=false)
	private Long createBy;
	
	@Column
	private Date lastUpdateDate;
	
	@Column(length=32, nullable=true)
	private Long lastUpdateBy;
	
	@JsonIgnore
	@OneToMany(mappedBy="roleId", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<Authorization> authorization;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Long getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(Long lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Set<Authorization> getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Set<Authorization> authorization) {
		this.authorization = authorization;
	}
	
	
}
