/**
 * 
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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author gmc
 *
 */
@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="basic_account", indexes={@Index(columnList="id", unique=true),
										@Index(columnList="account_code", unique=true)})
public class BasicAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false, unique=true)
	private Long accountId;
	
	@Column(name="account_code", length=225, nullable=false)
	private String accountCode;
	
	@Column(name="account_name", length=225, nullable=true)
	private String accountName;
	
	@JsonIgnore
	@Column(name = "acc_password", length=100, nullable=false)
	private String accPassword;
	
	@Column(name="sex", length=10, nullable=true)
	private Character accSex;
	
	@Column(name="register_date", nullable=false)
	private Date registerDate;
	
	@Column(name="email", length=255, nullable=true)
	private String email;
	
	@Column(name="tel", length=20, nullable=true)
	private String tel;
	
	@Column(name="wechat", length=255, nullable=true)
	private String wechat;
	
	@Column(name="create_date", nullable=false)
	private Date createDate;
	
	@Column(name="create_by", nullable=false)
	private Long createBy;
	
	@Column(name="last_update_date", nullable=true)
	private Date lastUpdateDate;
	
	@Column(name="last_update_by", nullable=true)
	private Long lastUpdateBy;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="PlatformUserId", insertable=true, updatable=true, nullable=true)
	private BasicUser user;
	
	@Transient
	private String isFemale;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Character getAccSex() {
		return accSex;
	}

	public void setAccSex(Character accSex) {
		this.accSex = accSex;
		if(accSex == 'F') {
			this.isFemale = "女";
		}else {
			this.isFemale = "男";
		}
	}
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
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

	public BasicUser getUser() {
		return user;
	}

	public void setUser(BasicUser user) {
		this.user = user;
	}

	public String getAccPassword() {
		return accPassword;
	}

	public void setAccPassword(String accPassword) {
		this.accPassword = accPassword;
	}
	
	public String getIsFemale() {
		if(this.getAccSex() == 'F') {
			isFemale = "女";
		} else {
			isFemale = "男";
		}
		return isFemale;
	}

	public void setIsFemale(String isFemale) {
		this.isFemale = isFemale;
	}
	
}
