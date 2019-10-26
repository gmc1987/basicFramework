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
import javax.persistence.ManyToOne;
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
@Table(name="basic_menus", indexes= {@Index(columnList="menu_id", unique=true),
										@Index(columnList="menu_code", unique=true),
										@Index(columnList="menu_level", unique=false),
										@Index(columnList="menu_sort", unique=false)})
public class BasicMenus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="menu_id", nullable=false)
	private Long menuId;
	
	@Column(name="menu_code", length=32, nullable=false)
	private String menuCode;
	
	@Column(name="menu_name", length=225, nullable=false)
	private String menuName;
	
	@Column(name="menu_path", length=500, nullable=true)
	private String menuPath;
	
	@Column(name="menu_type", nullable=false)
	private Integer menuType;
	
	@Column(name="menu_sort", nullable=true)
	private Integer menuSort;
	
	@Column(name="icon", nullable=true)
	private String icon;
	
	@Column(name="create_date", nullable=false)
	private Date createDate;
	
	@Column(name="create_by", nullable=false)
	private Long createBy;
	
	@Column(name="last_update_date", nullable=true)
	private Date LastUpdateDate;
	
	@Column(name="last_update_by", nullable=true)
	private Long lastUpdateBy;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_menu")
	private BasicMenus parentMenu;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="parentMenu")
	private Set<BasicMenus> childMenus;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="menu")
	private Set<BasicAuthorization> authorizations;
	
	/**
	 * 菜单层级
	 */
	@Column(name="menu_level", nullable=false)
	private Integer menuLevel;

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Integer getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(Integer menuSort) {
		this.menuSort = menuSort;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public BasicMenus getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(BasicMenus parentMenu) {
		this.parentMenu = parentMenu;
	}

	public Set<BasicMenus> getChildMenus() {
		return childMenus;
	}

	public void setChildMenus(Set<BasicMenus> childMenus) {
		this.childMenus = childMenus;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Set<BasicAuthorization> getAuthorizations() {
		return authorizations;
	}

	public void setAuthorizations(Set<BasicAuthorization> authorizations) {
		this.authorizations = authorizations;
	}

}
