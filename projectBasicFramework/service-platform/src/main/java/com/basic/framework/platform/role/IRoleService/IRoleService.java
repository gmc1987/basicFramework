/**
 * 
 */
package com.basic.framework.platform.role.IRoleService;

import java.util.List;

import com.basic.framework.platform.basic.IbaseService.IBasicService;
import com.basic.framework.platform.role.dto.RoleDto;
import com.basic.framework.platform.role.pojo.Role;


/**
 * @author gmc
 *
 */
public interface IRoleService extends IBasicService<Role> {
	
	public Role findRoleByCode(String roleCode) throws Exception;
	
	public List<Role> findRoleByKey(RoleDto dto)  throws Exception;
	
	public void saveRole(RoleDto dto)  throws Exception;
	
	public void delRole(RoleDto dto) throws Exception;
	
}
