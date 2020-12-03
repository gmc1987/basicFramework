/**
 * 
 */
package com.basic.framework.platform.menus.IService;

import java.util.List;

import com.basic.framework.platform.basic.IbaseService.IBasicService;
import com.basic.framework.platform.basic.SideException.CustException;
import com.basic.framework.platform.menus.dto.MenuDto;
import com.basic.framework.platform.menus.pojo.Menus;

/**
 * @author gmc
 * @see 提供功能菜单查询方法
 */
public interface IMenuService extends IBasicService<Menus> {
	
	/**
	 * 获取所有父级菜单
	 * @return
	 */
	public List<Menus> getParents() throws Exception;
	
	/**
	 * 根据父级菜单获取所有子级菜单
	 * @param parentId
	 * @return
	 */
	public List<Menus> getChildByParentId(Integer parentId) throws CustException;
	
	/**
	 * 根据条件查询菜单
	 * @param dto
	 * @return
	 */
	public List<Menus> findMenuByKey(MenuDto dto) throws CustException;
	
	/**
	 * 根据dto对象操作菜单
	 * @param dto
	 */
	public void editMenuByDto(MenuDto dto) throws CustException;
	
	/**
	 * 根据dto删除菜单对象
	 * @param dto
	 * @throws SideCustException
	 */
	public void delMenuByDto(MenuDto dto) throws CustException;
	
	/**
	 * 根据类型查询子菜单
	 * @param menuType
	 * @return
	 * @throws SideCustException
	 */
	public List<Menus> getChildMenusByMenuType(MenuDto dto) throws CustException;

}
