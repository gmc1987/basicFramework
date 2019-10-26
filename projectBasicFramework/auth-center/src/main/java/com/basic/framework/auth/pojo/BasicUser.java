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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author gmc
 *
 */

@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="basic_user",indexes= {@Index(columnList="user_id", unique=true), 
								@Index(columnList="user_code", unique=true),
								@Index(columnList="last_login_date", unique=false),
								@Index(columnList="user_status", unique=false)})
public class BasicUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Long userId;
	
	@Column(name="user_code", length=225, nullable=false)
	private String userCode;
	
	@Column(length=225, nullable=false)
	private String password;
	
	@Column(name="nick_name", length=255, nullable=false)
	private String nickName;
	
	@Column(name="last_login_date", nullable=true)
	private Date lastLoginDate;
	
	/**
	 * 用户状态 0-正常，1-失效
	 */
	@Column(name="user_status", nullable=false)
	private Integer userStatus;
	
	/**
	 * 用户锁定状态 false-未锁定，true-锁定
	 */
	@Column(nullable=false)
	private boolean isLock;
	
	@Column(name="open_id", length=225, nullable=true)
	private String openId;
	
	@Column(name="create_date", nullable=false)
	private Date createDate;
	
	@Column(name="create_by", nullable=false)
	private Long createBy;
	
	@Column(name="last_update_date", nullable=true)
	private Date lastUpdateDate;
	
	@Column(name="last_update_by", nullable=true)
	private Long lastUpdateBy;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="user")
	private Set<BasicUserRole> userRole;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="account_id", insertable=true, updatable=true, nullable=true)
	private BasicAccount account;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public boolean isLock() {
		return isLock;
	}

	public void setLock(boolean isLock) {
		this.isLock = isLock;
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Set<BasicUserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<BasicUserRole> userRole) {
		this.userRole = userRole;
	}

	public BasicAccount getAccount() {
		return account;
	}

	public void setAccount(BasicAccount account) {
		this.account = account;
	}

}
