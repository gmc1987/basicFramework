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
import org.springframework.web.bind.annotation.RestController;

import com.basic.framework.platform.authorization.IService.IAuthorizationService;
import com.basic.framework.platform.authorization.dto.AuthorizationDto;
import com.basic.framework.platform.authorization.pojo.Authorization;
import com.basic.framework.platform.basic.constant.PlatformConstant;
import com.basic.framework.platform.basic.controller.ServiceBaseController;
import com.basic.framework.platform.basic.dto.ResultDto;
import com.basic.framework.platform.menus.IService.IMenuService;
import com.basic.framework.platform.role.IRoleService.IRoleService;

/**
 * @author gmc
 * @see 负责处理角色权限
 */
@RestController
@RequestMapping("/authorization")
public class AuthorizationController extends ServiceBaseController {
	
	@Qualifier("authorizationService")
	@Autowired
	private IAuthorizationService authorizationService;
	
	@Qualifier("menuservice")
	@Autowired
	private IMenuService menuService;
	
	@Qualifier("roleService")
	@Autowired
	private IRoleService roleService;
	
	/**
	 * 查询角色权限
	 * @param authorization
	 * @return
	 */
	@GetMapping("/roleInfo")
	public ResultDto<Authorization> findAuthorizationByMenuAndRole(AuthorizationDto authorization) {
		ResultDto<Authorization> result = new ResultDto<Authorization>();
		try {
			List<Authorization> list = authorizationService.findAuthorizationByRoleAndMenu(authorization);
			result.setRetCode(PlatformConstant.SUCCESS);
			result.setRecord(list);
			result.setRetMsg(PlatformConstant.SUCCESS_MSG);
		} catch(Exception e) {
			result.setRetCode(PlatformConstant.FAIL);
			result.setRetMsg(PlatformConstant.FAIL_MSG);
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 保存角色权限
	 * @param authorizationDto
	 * @return
	 */
	@PostMapping("/saveAuthorization")
	public ResultDto<Authorization> saveAuthorization(@RequestBody List<AuthorizationDto> authorization) {
		ResultDto<Authorization> result = new ResultDto<Authorization>();
		try {
			authorizationService.saveAllAuthorizations(authorization);
			result.setRetCode(PlatformConstant.SUCCESS);
			result.setRecord(null);
			result.setRetMsg(PlatformConstant.SUCCESS_MSG);
		} catch(Exception e) {
			result.setRetCode(PlatformConstant.FAIL);
			result.setRetMsg(PlatformConstant.FAIL_MSG);
			e.printStackTrace();
		}
		return result;
	}
	
	@GetMapping("/delAuthorization")
	public ResultDto<Authorization> delAuthorization(AuthorizationDto authorizationDto) {
		ResultDto<Authorization> result = new ResultDto<Authorization>();
		
		return result;
	}
	
}
