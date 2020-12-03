/**
 * 
 */
package com.basic.framework.platform.users.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gmc
 *
 */
@Controller
@RequestMapping("/user")
public class AdminUserController {
	
	@RequestMapping("/userInfo")
	@ResponseBody
	public Principal user(Principal user) {
        return user;
    }
}
