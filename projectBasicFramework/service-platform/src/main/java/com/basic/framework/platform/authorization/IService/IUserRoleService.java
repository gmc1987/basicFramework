/**
 * 
 */
package com.basic.framework.platform.authorization.IService;

import java.util.List;

import com.basic.framework.platform.authorization.dto.RoleUserDto;
import com.basic.framework.platform.authorization.pojo.UserRole;
import com.basic.framework.platform.basic.IbaseService.IBasicService;

/**
 * @author gmc
 *
 */
public interface IUserRoleService extends IBasicService<UserRole> {
	
	public List<UserRole> findUserRoleByUserId(Long userId);
	
	public void saveUserRole(List<RoleUserDto> list) throws Exception;

}
