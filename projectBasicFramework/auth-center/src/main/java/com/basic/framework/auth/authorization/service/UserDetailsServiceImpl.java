/**
 * 
 */
package com.basic.framework.auth.authorization.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.basic.framework.auth.authorization.model.AuthUserEntity;
import com.basic.framework.auth.authorization.userdetails.BasicAuthUserDetails;
import com.basic.framework.auth.pojo.BasicAuthorization;
import com.basic.framework.auth.pojo.BasicMenus;
import com.basic.framework.auth.pojo.BasicRole;
import com.basic.framework.auth.pojo.BasicUserRole;
import com.basic.framework.auth.pojo.PlatformUser;
import com.basic.framework.auth.service.BasicAuthorizationService;
import com.basic.framework.auth.service.BasicUserRoleService;
import com.basic.framework.auth.service.BasicUserService;

/**
 * @author gmc
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private BasicUserService basicUserService;

	@Autowired
	private BasicUserRoleService basicUserRoleService;

	@Autowired
	private BasicAuthorizationService basicAuthorizationService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BasicAuthUserDetails basicAuthUserDetails = null;
		AuthUserEntity authUserEntity = null;
		User user = null;
		Map<String, List<BasicAuthorization>> authorizationMap = new HashMap<String, List<BasicAuthorization>>();
		Map<String, List<BasicMenus>> menusMap = new HashMap<String, List<BasicMenus>>();
		List<BasicAuthorization> authorizations = null;
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		if (StringUtils.isEmpty(username)) {
			throw new UsernameNotFoundException("用户名[usernname]为空!");
		}
		try {
			PlatformUser authUser = basicUserService.findUserByCode(username);
			List<BasicUserRole> userRoles = basicUserRoleService.findByUser(authUser);
			// 加载用户角色
			for (BasicUserRole userRole : userRoles) {
				BasicRole role = userRole.getRoleId();
				authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
				// 加载角色权限
				authorizations = basicAuthorizationService.findByRole(role);
				authorizationMap.put(role.getRoleCode(), authorizations);
				// 加载角色菜单
				List<BasicMenus> menus = new ArrayList<BasicMenus>();
				for (BasicAuthorization authorization : authorizations) {
					menus.add(authorization.getMenuId());
				}
				menusMap.put(role.getRoleCode(), menus.size() > 0 ? menus : null);
			}
			user = new User(authUser.getUserCode(), authUser.getAccount().getAccPassword(), authorities);
			authUserEntity = new AuthUserEntity(authUser, userRoles.get(0).getRoleId(), authUser.getAccount(), menusMap, userRoles,
					authorizationMap);
			basicAuthUserDetails = new BasicAuthUserDetails(user, authUserEntity);

		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("没有找到该用户信息");
		}

		return basicAuthUserDetails;
	}

}
