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

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author gmc
 * @see 帐户实体
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
@Entity
@DynamicUpdate
@Table(name="basic_account", indexes= {@Index(columnList = "id"), 
		@Index(columnList = "accCode"), 
		@Index(columnList = "accName"),
		@Index(columnList = "accStatus")})
public class Account implements Serializable {
	
	public Account() {}
	
	public Account(Long accountId) {
		this.accountId = accountId;
	}
	
	public Account( String accCode, String accName, String accPassword, 
			String tel, Character accSex, Date accBirthday, Integer accStatus) {
		this.accCode = accCode;
		this.accName = accName;
		this.accPassword = accPassword;
		this.tel = tel;
		this.accSex = accSex;
		this.accBirthday = accBirthday;
		this.accStatus = accStatus;
	}
	
	public Account(Long accountId, String accCode, String accName, String accPassword, String tel) {
		this.accountId = accountId;
		this.accCode = accCode;
		this.accName = accName;
		this.accPassword = accPassword;
		this.tel = tel;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long accountId;
	
	@Column(length=32, nullable=false)
	private String accCode;
	
	@Column(length=100, nullable=false)
	private String accName;
	
	@JsonIgnore
	@Column(length=100, nullable=false)
	private String accPassword;
	
	@Column(length=20, nullable=true)
	private String tel;
	
	@Column(nullable=true)
	private Character accSex;
	
	@Column(nullable=true)
	private Date accBirthday;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="PlatformUserId", insertable=true, nullable=true)
	private PlatformUser PlatformUserId;
	
	@Column(nullable=false)
	private Date createDate;
	
	@Column(nullable=false)
	private Long createBy;
	
	@JsonIgnore
	@Column(nullable=true)
	private Date lastUpdateDate;
	
	@JsonIgnore
	@Column(length=32, nullable=true)
	private Long lastUpdateBy;
	
	@Column(nullable=false)
	private Integer accStatus;
	
	@Transient
	private String isFemale;

	public String getAccCode() {
		return accCode;
	}

	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getAccPassword() {
		return accPassword;
	}

	public void setAccPassword(String accPassword) {
		this.accPassword = accPassword;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public Date getAccBirthday() {
		return accBirthday;
	}

	public void setAccBirthday(Date accBirthday) {
		this.accBirthday = accBirthday;
	}

	public PlatformUser getPlatformUserId() {
		return PlatformUserId;
	}

	public void setPlatformUserId(PlatformUser PlatformUserId) {
		this.PlatformUserId = PlatformUserId;
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

	public Integer getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(Integer accStatus) {
		this.accStatus = accStatus;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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
