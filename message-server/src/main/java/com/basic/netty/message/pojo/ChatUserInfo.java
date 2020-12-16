/**
 * 
 */
package com.basic.netty.message.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author gmc
 * @see 用户信息实体
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "chat_user_info")
public class ChatUserInfo implements Serializable {
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	/**
	 * 用户id
	 */
	@Column(name = "user_id", nullable = false)
	private Long userId;
	
	/**
	 * 用户名
	 */
	@Column(name = "user_name", length = 200, nullable = false)
	private String userName;
	
	/**
	 * 公钥
	 */
	@Column(name = "public_key", length = 1000, nullable = false)
	private String key;
	
	/**
	 * 登录状态
	 */
	@Column(name = "login_status", length = 4, nullable = false)
	private Integer loginStatus;
	
	/**
	 * 头像信息
	 */
	@Column(name = "picture", length = 500, nullable = false)
	private String picture;
	
	@Column(name = "create_date", nullable = false)
	private Date createDate;
	
	@Column(name = "create_by", nullable = false)
	private Long createBy;
	
	@Column(name = "last_create_date", nullable = false)
	private Date lastCreateDate;
	
	@Column(name = "last_create_by", nullable = false)
	private Long lastCreateBy;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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

	public Date getLastCreateDate() {
		return lastCreateDate;
	}

	public void setLastCreateDate(Date lastCreateDate) {
		this.lastCreateDate = lastCreateDate;
	}

	public Long getLastCreateBy() {
		return lastCreateBy;
	}

	public void setLastCreateBy(Long lastCreateBy) {
		this.lastCreateBy = lastCreateBy;
	}
	
}
