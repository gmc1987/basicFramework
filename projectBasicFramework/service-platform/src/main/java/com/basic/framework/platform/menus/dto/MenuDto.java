/**
 * 
 */
package com.basic.framework.platform.menus.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gmc
 * @see 菜单参数对象
 */
@SuppressWarnings("serial")
public class MenuDto implements Serializable {

	private String menuId;
	
	private String menuCode;
	
	private String menuName;
	
	private String parentId;
	
	private String menuPath;
	
	private String menuType;
	
	private Integer menuSort;
	
	private String icon;
	
	private Integer isParent;
	
	private Integer parentMenu;
	
	private String key;
	
	private String currentUser;
	
	@SuppressWarnings("unused")
	private Date date;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
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

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	public Date getDate() {
		return new Date();
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Integer parentMenu) {
		this.parentMenu = parentMenu;
	}
}
