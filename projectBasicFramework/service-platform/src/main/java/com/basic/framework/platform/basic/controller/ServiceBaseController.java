/**
 * 
 */
package com.basic.framework.platform.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import com.basic.framework.platform.basic.dto.UserInfo;
import com.basic.framework.platform.users.IService.IUserService;
import com.basic.framework.platform.users.pojo.PlatformUser;


/**
 * @author gmc
 * @see 基础控制器
 */

@RestController
public class ServiceBaseController {
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	protected UserInfo getUserInfo() {
		UserInfo userInfo = new UserInfo();
		
		String userName = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!StringUtils.isEmpty(userName)) {
			PlatformUser user = userService.findUserByCode(userName);
			if(user != null) {
				userInfo.setUser(user);
			}
		}
		
		return userInfo;
	}
}
