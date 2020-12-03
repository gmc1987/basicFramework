/**
 * 
 */
package com.basic.framework.platform.basic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.basic.framework.platform.authorization.IService.IAuthorizationService;
import com.basic.framework.platform.authorization.pojo.Authorization;
import com.basic.framework.platform.basic.constant.PlatformConstant;
import com.basic.framework.platform.basic.dto.ResultDto;
import com.basic.framework.platform.menus.IService.IMenuService;
import com.basic.framework.platform.menus.pojo.Menus;
import com.basic.framework.platform.users.IService.IUserService;
import com.basic.framework.platform.users.pojo.PlatformUser;

/**
 * @author gmc
 * @see 主业数据rest接口
 */

@RestController
public class IndexController {
	
	@Autowired
	@Qualifier("authorizationService")
	private IAuthorizationService authorizationService;
	
	@Autowired
	@Qualifier("menuservice")
	private IMenuService MenuService;
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	@RequestMapping("/getAllParentMenus")
	public List<Authorization> getAllParentMenus() {
		List<Authorization> authorizationList = new ArrayList<Authorization>();
		try {
			@SuppressWarnings("unchecked")
			List<GrantedAuthority> roleList = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			if(!roleList.isEmpty()) {
				authorizationList = authorizationService.findParentAuthorizationByRole(roleList.get(0).getAuthority());
			}
			return authorizationList;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@RequestMapping("/getChildParentMenus")
	public ResultDto<Menus> getChildMenuByParent(@RequestParam("parentId") int parentId){
		ResultDto<Menus> result = null;
		try {
			result = new ResultDto<Menus>(PlatformConstant.SUCCESS, PlatformConstant.SUCCESS_MSG, MenuService.getChildByParentId(parentId));
			return result;
		} catch(Exception e) {
			result = new ResultDto<Menus>(PlatformConstant.FAIL, PlatformConstant.FAIL_MSG);
			return result;
		}
	}
	
	@RequestMapping("/getUserInfo")
	public PlatformUser getUsers() {
		String userCode = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.findUserByCode(userCode);
	}
	
}
