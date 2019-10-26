/**
 * 
 */
package com.basic.framework.auth.pojo;

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
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author gmc
 *
 */

@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="basic_role", indexes= {@Index(columnList="role_id", unique=true),
									@Index(columnList="role_code", unique=true)})
public class BasicRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id", nullable=false)
	private Long roleId;
	
	@Column(name="role_code", length=32, nullable=false)
	private String roleCode;
	
	@Column(name="role_name", length=225, nullable=false)
	private String roleName;
	
	@Column(name="role_status",  nullable=false)
	private Integer roleStatus;
	
	@Column(name="create_date", nullable=false)
	private Date createDate;
	
	@Column(name="create_by", nullable=false)
	private Long createBy;
	
	@Column(name="last_update_date", nullable=true)
	private Date LastUpdateDate;
	
	@Column(name="last_update_by", nullable=true)
	private Long lastUpdateBy;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="role")
	private Set<BasicAuthorization> authorization;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="role")
	private Set<BasicUserRole> userRole;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
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
		return LastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		LastUpdateDate = lastUpdateDate;
	}

	public Long getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(Long lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Set<BasicAuthorization> getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Set<BasicAuthorization> authorization) {
		this.authorization = authorization;
	}

	public Set<BasicUserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<BasicUserRole> userRole) {
		this.userRole = userRole;
	}
	
}
