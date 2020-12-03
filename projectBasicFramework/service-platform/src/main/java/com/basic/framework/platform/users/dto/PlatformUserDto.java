/**
 * 
 */
package com.basic.framework.platform.users.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gmc
 * @see 用户对象参数，用于接收前端参数
 */
@SuppressWarnings("serial")
public class PlatformUserDto implements Serializable {
	
	public PlatformUserDto() {
	}

	public PlatformUserDto(Long userId, String userCode, String userName, Integer userStatus, Long account,
			String roleId, String roleName, Long createBy, Date createDate) {
		this.userId = userId;
		this.userCode = userCode;
		this.userName = userName;
		this.userStatus = userStatus;
		this.account = account;
		this.roleId = roleId;
		this.roleName = roleName;
		this.createBy = createBy;
		this.createDate = createDate;
	}

	private Long userId;
	
	private String userCode;
	
	private String userName;
	
	private Integer userStatus;
	
	private Long account;
	
	private String roleId;
	
	private String roleName;
	
	private Long createBy;
	
	private Date createDate;
	
	private String formatDate;
	
	private String formatStatus;
	
	private String searchKey;

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

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFormatDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(this.createDate != null) {
			formatDate = format.format(getCreateDate());
			return formatDate;
		} else {
			formatDate = format.format(getCreateDate());
			return formatDate;
		}
	}

	public void setFormatDate(String formatDate) {
		this.formatDate = formatDate;
	}

	public String getFormatStatus() {
		switch (this.getUserStatus()) {
		case 0:
			formatStatus = "失效";
			break;
		case 1:
			formatStatus = "正常";
			break;
		case 2:
			formatStatus = "冻结";
			break;
		default:
			formatStatus = "正常";
			break;
		}
		return formatStatus;
	}

	public void setFormatStatus(String formatStatus) {
		this.formatStatus = formatStatus;
	}
	
}
