/**
 * 
 */
package com.basic.framework.platform.authorization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.basic.framework.platform.authorization.IService.IUserRoleService;
import com.basic.framework.platform.authorization.dto.RoleUserDto;
import com.basic.framework.platform.authorization.pojo.UserRole;
import com.basic.framework.platform.basic.constant.PlatformConstant;
import com.basic.framework.platform.basic.controller.ServiceBaseController;
import com.basic.framework.platform.basic.dto.ResultDto;

/**
 * @author gmc
 * @see 用户角色关系维护
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController extends ServiceBaseController {
	
	@Qualifier("userRoleService")
	@Autowired
	private IUserRoleService userRoleService;

	@GetMapping("/getUserRole")
	public ResultDto<UserRole> getRoleByUser(@RequestParam("userId") Long userId){
		ResultDto<UserRole> resultDto = new ResultDto<UserRole>();
		try {
			resultDto.setRecord(userRoleService.findUserRoleByUserId(userId));
			resultDto.setRetCode(PlatformConstant.SUCCESS);
			resultDto.setRetMsg(PlatformConstant.SUCCESS_MSG);
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setRetCode(PlatformConstant.FAIL);
			resultDto.setRetMsg(PlatformConstant.FAIL_MSG);
		}
		
		return resultDto;
	}
	
	@PostMapping("/saveRoleUser")
	public ResultDto<UserRole> saveRoleUser(@RequestBody List<RoleUserDto> dtoList){
		ResultDto<UserRole> resultDto = new ResultDto<UserRole>();
		try {
			userRoleService.saveUserRole(dtoList);
			resultDto.setRetCode(PlatformConstant.SUCCESS);
			resultDto.setRetMsg(PlatformConstant.SUCCESS_MSG);
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setRetCode(PlatformConstant.FAIL);
			resultDto.setRetMsg(PlatformConstant.FAIL_MSG);
		}
		return resultDto;
	}
}
