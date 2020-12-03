/**
 * 
 */
package com.basic.framework.platform.basic.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basic.framework.platform.authorization.IService.IAuthorizationService;
import com.basic.framework.platform.authorization.IService.IUserRoleService;
import com.basic.framework.platform.authorization.pojo.Authorization;
import com.basic.framework.platform.menus.pojo.Menus;
import com.basic.framework.platform.users.IService.IAccountService;
import com.basic.framework.platform.users.IService.IUserService;
import com.basic.framework.platform.users.pojo.PlatformUser;

/**
 * @author gmc
 *
 */
@RestController
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	@Qualifier("userService")
	private IUserService UserService;
	
	@Autowired
	@Qualifier("accountService")
	private IAccountService AccountService;
	
	@Autowired
	@Qualifier("authorizationService")
	private IAuthorizationService authorizationService;
	
	@Autowired
	@Qualifier("userRoleService")
	private IUserRoleService userRoleService;

	@RequestMapping(value="/dologin")
	public Map<String, Object> doLogin(ModelMap mode, Principal principal, HttpSession session) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<Authorization> authorizations = null;
		List<Menus> parents = null;
		List<Menus> firstChilds = null;
		
		//获取用户名
		String userName = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//获取角色
		@SuppressWarnings("unchecked")
		List<GrantedAuthority> roleList = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		List<String> roles = new ArrayList<String>();
		PlatformUser user = null;
		try {
			if(!roleList.isEmpty()) {
				Iterator<GrantedAuthority> iterator =  roleList.iterator();
				while(iterator.hasNext()) {
					String roleCode = iterator.next().toString();
					roles.add(roleCode);
				}
			}
			//获取用户权限
			if(!roles.isEmpty()) {
				authorizations = authorizationService.findAuthorizationByRole(roles.get(0));
				parents = getAllParentMenu(authorizations);
				firstChilds = getFirstParentMenuChild(authorizations);
			}
			
			//获取用户信息
			if(!StringUtils.isEmpty(userName)) {
				user = UserService.findUserByCode(userName);
				if(user != null) {
					result.put("user_info", user);
				}
			}
			
			//用户角色权限
			result.put("parents", parents);
			result.put("firstChilds", firstChilds);
			return result;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private List<Menus> getAllParentMenu(List<Authorization> authorizations){
		List<Menus> parents = new ArrayList<Menus>();
		if(!authorizations.isEmpty()) {
			for(Authorization authorization : authorizations) {
				if(authorization.getMenuId().getIsParent() == 0) {
					parents.add(authorization.getMenuId());
				}
			}
		}
		return parents;
	}
	
	private List<Menus> getFirstParentMenuChild(List<Authorization> authorizations){
		List<Menus> childs = new ArrayList<Menus>();
		if(!authorizations.isEmpty()) {
			Authorization first = null;
			int index = 0;
			for(Authorization authorization : authorizations) {
				if(authorization.getMenuId().getIsParent() == 0 && index == 0) {
					first = authorization;
					index++;
				}
				if(authorization.getMenuId().getIsParent() == 1) {
					if(authorization.getMenuId().getParentMenu().getMenuId() == first.getMenuId().getMenuId()) {
						childs.add(authorization.getMenuId());
					}
				} else {
					continue;
				}
			}
		}
		return childs;
	}
	
}
