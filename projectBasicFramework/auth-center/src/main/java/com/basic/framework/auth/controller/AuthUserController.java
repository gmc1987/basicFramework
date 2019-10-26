/**
 * 
 */
package com.basic.framework.auth.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basic.framework.auth.authorization.model.AuthUserEntity;
import com.basic.framework.auth.authorization.userdetails.BasicAuthUserDetails;

/**
 * @author gmc
 * @see 用户信息返回对象
 */

@EnableResourceServer
@RestController
@RequestMapping()
public class AuthUserController {

	@PostMapping("/user")
	public AuthUserEntity userInfo() {
		AuthUserEntity authUser = ((BasicAuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthUserEntity();
		return authUser;
	}
}
