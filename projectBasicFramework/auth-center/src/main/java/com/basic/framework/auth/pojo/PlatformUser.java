/**
 * 增加@SqlResultSetMapping定义SideUserDto对象为数据映射对象，SideUserDto对象必须存在带参数的构造方法
 * 参数名字、个数与SQL结果集必须一致。参数个数可以小于结果集字段数目
 * 对于某些数据库，需要制定字段映射类型
 * 对象映射名称以类名为主，底层dao根据类型寻找映射
 */
package com.basic.framework.auth.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author gmc
 * 
 */
@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
@DynamicUpdate
@Entity
@Table(name="basic_user")
public class PlatformUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public PlatformUser() {}
	
	public PlatformUser(Long userId) {
		this.userId = userId;
	}
	
	public PlatformUser(String userCode, String userName, Integer userStatus, Integer isSystemUser) {
		this.userCode = userCode;
		this.userName = userName;
		this.userStatus = userStatus;
		this.isSystemUser = isSystemUser;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long userId;
	
	@Column(length=32, nullable=false)
	private String userCode;
	
	@Column(length=100, nullable=false)
	private String userName;
	
	@Column
	private Date createDate;
	
	@Column
	private Date lastUpdateDate;
	
	@Column(length=32, nullable=false)
	private Long createBy;
	
	@Column(length=32, nullable=true)
	private Long lastUpdateBy;
	
	/**
	 * 0-正常，1-冻结，2-失效
	 */
	@Column(nullable=false)
	private Integer userStatus;
	
	/**
	 * 0-系统用户，1-普通用户
	 */
	@Column(nullable=false)
	private Integer isSystemUser;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="account", insertable=true, nullable=true)
	private Account account;

	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(Long lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getIsSystemUser() {
		return isSystemUser;
	}

	public void setIsSystemUser(Integer isSystemUser) {
		this.isSystemUser = isSystemUser;
	}

}
