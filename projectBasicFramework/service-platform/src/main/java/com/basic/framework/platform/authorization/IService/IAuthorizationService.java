/**
 * 
 */
package com.basic.framework.platform.authorization.IService;

import java.util.List;

import com.basic.framework.platform.authorization.dto.AuthorizationDto;
import com.basic.framework.platform.authorization.pojo.Authorization;
import com.basic.framework.platform.basic.IbaseService.IBasicService;

/**
 * @author gmc
 *
 */
public interface IAuthorizationService extends IBasicService<Authorization> {
	
	/**
	 * 根据角色编码查询角色权限
	 * @param roleCode
	 * @return
	 */
	public List<Authorization> findAuthorizationByRole(String roleCode) throws Exception;
	
	/**
	 * 根据角色编码查询所有父级菜单权限
	 * @param roleCode
	 * @return
	 */
	public List<Authorization> findParentAuthorizationByRole(String roleCode) throws Exception;
	
	/**
	 * 根据角色与菜单查询菜单权限
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public List<Authorization> findAuthorizationByRoleAndMenu(AuthorizationDto dto) throws Exception;
	
	/**
	 * 保存所有的角色权限
	 * @see 判断authorization对象中的operationList属性，如果该属性为空，并且id不为空。将当作要删除权限。
	 * @param list
	 * @throws Exception
	 */
	public void saveAllAuthorizations(List<AuthorizationDto> list) throws Exception;

}
