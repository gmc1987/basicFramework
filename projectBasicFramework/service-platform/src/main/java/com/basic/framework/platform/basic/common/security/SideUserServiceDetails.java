///**
// * 
// */
//package com.side.basic.common.security;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.side.admin.IService.ISideAccountService;
//import com.side.admin.IService.ISideAdminUserService;
//import com.side.admin.pojo.AdminUser;
//import com.side.authorization.IService.IAuthorizationService;
//import com.side.authorization.IService.IUserRoleService;
//import com.side.authorization.pojo.SideUserRole;
//import com.side.basic.common.utils.DetachedCriteriaTS;
//
///**
// * @author gmc
// *
// */
//@Service("sideUserDetailService")
//public class SideUserServiceDetails implements UserDetailsService {
//	
//	@Qualifier("sideAccountService")
//	@Autowired
//	private ISideAccountService sideAccountService;
//	
//	@Qualifier("sideAdminUserService")
//	@Autowired
//	private ISideAdminUserService sideAdminUserService;
//	
//	@Qualifier("userRoleService")
//	@Autowired
//	private IUserRoleService userRoleService;
//	
//	@Qualifier("authorizationService")
//	@Autowired
//	private IAuthorizationService authorizationService;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//		
//		AdminUser adminUser = sideAdminUserService.findAdminUserByAdminCode(username);
//		DetachedCriteriaTS<SideUserRole> criteria = new DetachedCriteriaTS<SideUserRole>(SideUserRole.class);
//		List<SideUserRole> roles = userRoleService.findAll(criteria);
//		
//		if(adminUser == null) {
//			throw new UsernameNotFoundException("不存在该用户!	");
//		}
//		
//		for(SideUserRole role : roles) {
//			authorities.add(new SimpleGrantedAuthority(role.getRoleId().getRoleCode()));
//		}
//		
//		return new org.springframework.security.core.userdetails.User(adminUser.getAdminCode(),
//				adminUser.getAccount().getAccPassword(), authorities);
//		
//	}
//
//
//}
