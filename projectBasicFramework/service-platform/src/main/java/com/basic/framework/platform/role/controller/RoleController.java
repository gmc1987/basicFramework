/**
 * 
 */
package com.basic.framework.platform.role.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.basic.framework.platform.basic.constant.PlatformConstant;
import com.basic.framework.platform.basic.controller.ServiceBaseController;
import com.basic.framework.platform.basic.dto.ResultDto;
import com.basic.framework.platform.basic.dto.UserInfo;
import com.basic.framework.platform.role.IRoleService.IRoleService;
import com.basic.framework.platform.role.dto.RoleDto;
import com.basic.framework.platform.role.pojo.Role;

/**
 * @author gmc
 * @see 角色控制器
 */

@RestController
@RequestMapping("/role")
public class RoleController extends ServiceBaseController{
	
	@Qualifier("roleService")
	@Autowired
	private IRoleService roleService;

	@RequestMapping("/list")
	public ResultDto<Role> getList(RoleDto dto){
		
		ResultDto<Role> result = new ResultDto<Role>();
		try {
			List<Role> list = roleService.findRoleByKey(dto);
			
			if(!ObjectUtils.isEmpty(list)) {
				result.setRetCode(PlatformConstant.SUCCESS);
				result.setRecord(list);
			} else {
				result.setRetCode(PlatformConstant.QUERY_EMPTY);
				result.setRetMsg(PlatformConstant.QUERY_EMPTY_MSG);
			}
		} catch(Exception e) {
			result.setRetCode(PlatformConstant.FAIL);
			result.setRetMsg(PlatformConstant.FAIL_MSG);
		}
		
		return result;
		
	}
	
	@RequestMapping(method=RequestMethod.POST, name="saveRole", value="saveRole")
	public ResultDto<Role> saveRole(@RequestBody RoleDto dto){
		
		UserInfo userInfo = getUserInfo();
		
		ResultDto<Role> result = new ResultDto<Role>();
		
		if (dto.getRoleId() != null) {
			dto.setLastUpdateBy(Long.parseLong(String.valueOf(userInfo.getUser().getUserId())));
			dto.setLastUpdateDate(new Date());
		} else {
			dto.setCreateBy(Long.parseLong(String.valueOf(userInfo.getUser().getUserId())));
			dto.setCreateDate(new Date());
		}
		
		try {
			roleService.saveRole(dto);
			result.setRetCode(PlatformConstant.SUCCESS);
			result.setRetMsg(PlatformConstant.SAVE_SUCCESS_MSG);
			
			dto = null;
			List<Role> list = roleService.findRoleByKey(dto);
			result.setRecord(list);
			
		} catch(Exception e) {
			e.printStackTrace();
			result.setRetCode(PlatformConstant.SAVE_FAIL);
			result.setRetMsg(PlatformConstant.SAVE_FAIL_MSG);
		}
		
		return result;
		
	}
	
	@RequestMapping("/delRole")
	public ResultDto<Role> delRole(RoleDto dto){
		
		ResultDto<Role> result = new ResultDto<Role>();
		try {
			roleService.delRole(dto);
			result.setRetCode(PlatformConstant.SUCCESS);
			result.setRetMsg(PlatformConstant.SUCCESS_MSG);
			
			dto = null;
			List<Role> list = roleService.findRoleByKey(dto);
			result.setRecord(list);
			
			
		} catch(Exception e) {
			e.printStackTrace();
			result.setRetCode(PlatformConstant.FAIL);
			result.setRetMsg(PlatformConstant.FAIL_MSG);
		}
		
		return result;
	}
	
}
