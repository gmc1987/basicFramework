/**
 * 
 */
package com.basic.framework.platform;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.basic.framework.platform.authorization.IService.IAuthorizationService;
import com.basic.framework.platform.authorization.IService.IUserRoleService;
import com.basic.framework.platform.authorization.dto.AuthorizationDto;
import com.basic.framework.platform.authorization.pojo.UserRole;
import com.basic.framework.platform.basic.config.DataSourceConfig;
import com.basic.framework.platform.basic.config.HibernateSessionConfig;
import com.basic.framework.platform.menus.IService.IMenuService;
import com.basic.framework.platform.menus.dto.MenuDto;
import com.basic.framework.platform.menus.pojo.Menus;
import com.basic.framework.platform.role.IRoleService.IRoleService;
import com.basic.framework.platform.role.pojo.Role;
import com.basic.framework.platform.users.IService.IUserService;
import com.basic.framework.platform.users.pojo.PlatformUser;

/**
 * @author gmc
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, HibernateSessionConfig.class})
@SpringBootTest(classes = ServicePlatformApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlatformAuthorizationTests {
	
	@Autowired
	@Qualifier("authorizationService")
	private IAuthorizationService authorizationService;
	
	@Autowired
	@Qualifier("menuservice")
	private IMenuService menuService;
	
	@Autowired
	@Qualifier("roleService")
	private IRoleService roleService;
	
	@Autowired
	@Qualifier("userRoleService")
	private IUserRoleService userRoleService;
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@Test
	public void authorizationTestCase() throws Exception {
		MenuDto menuDto = new MenuDto();
		List<AuthorizationDto> authorizationList = new ArrayList<AuthorizationDto>();
		
		List<Menus> menus = menuService.findMenuByKey(menuDto);
		Role role = roleService.get(Role.class, 1);
		String operationList = "add,update,delete,query,import,export";
		
		
		if(menus != null && menus.size() > 0) {
			for(Menus menu : menus) {
				AuthorizationDto dto = new AuthorizationDto();
				dto.setMenuId(menu.getMenuId());
				dto.setRoleId(role.getRoleId());
				dto.setOperationList(operationList);
				authorizationList.add(dto);
			}
			authorizationService.saveAllAuthorizations(authorizationList);
		}
	}
	
	
	@Test
	public void userRoleTestCase() throws Exception{
		Role role = roleService.get(Role.class, 1);
		PlatformUser user = userService.get(PlatformUser.class, 1L);
		UserRole roleUser = new UserRole();
		roleUser.setUserId(user);
		roleUser.setRoleId(role);
		userRoleService.save(roleUser);
	}

}
