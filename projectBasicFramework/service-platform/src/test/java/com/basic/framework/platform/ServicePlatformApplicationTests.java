package com.basic.framework.platform;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.basic.framework.platform.authorization.IService.IAuthorizationService;
import com.basic.framework.platform.authorization.pojo.Authorization;
import com.basic.framework.platform.basic.common.utils.DetachedCriteriaTS;
import com.basic.framework.platform.basic.config.DataSourceConfig;
import com.basic.framework.platform.basic.config.HibernateSessionConfig;
import com.basic.framework.platform.menus.IService.IMenuService;
import com.basic.framework.platform.menus.pojo.Menus;
import com.basic.framework.platform.role.IRoleService.IRoleService;
import com.basic.framework.platform.role.pojo.Role;
import com.basic.framework.platform.users.IService.IAccountService;
import com.basic.framework.platform.users.IService.IUserService;
import com.basic.framework.platform.users.pojo.Account;
import com.basic.framework.platform.users.pojo.PlatformUser;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, HibernateSessionConfig.class})
@SpringBootTest(classes = ServicePlatformApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServicePlatformApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@Autowired
	@Qualifier("accountService")
	private IAccountService accountService;
	
	@Autowired
	@Qualifier("roleService")
	private IRoleService roleService;
	
	@Autowired
	@Qualifier("menuservice")
	private IMenuService menuService;
	
	@Autowired
	@Qualifier("authorizationService")
	private IAuthorizationService authorizationService;
	
	@Test
	public void userCase() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		PlatformUser user = new PlatformUser("admin", "admin", 0, 0);
//		user.setCreateBy(1L);
//		user.setCreateDate(new Date());
//		
//		Account account = new Account("admin", "admin", encoder.encode("admin"), "18102810633", 'F', null, 0);
//		account.setCreateBy(1L);
//		account.setCreateDate(new Date());
//		
//		user.setAccount(account);
//		account.setPlatformUserId(user);
		
		PlatformUser user = userService.get(PlatformUser.class, 1L);
		Account account = accountService.get(Account.class, 1L);
		account.setAccPassword("admin");
		user.setAccount(account);
		
		try {
			userService.userEditer(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void roleCase() {
//		Role role = new Role("sysadmin", "系统管理员", 0, new Date(), 1L);
		Role role = new Role("company-manager", "企业管理员", 0, new Date(), 1L);
		roleService.save(role);
	}
	
	@Test
	public void authorizationCase() {
		Role role = roleService.get(Role.class, 1);
		DetachedCriteriaTS<Menus> detachedCriteria = new DetachedCriteriaTS<Menus>(Menus.class);
		List<Menus> menus = menuService.findAll(detachedCriteria);
		for(Menus menu : menus) {
			Authorization authorization = new Authorization();
			authorization.setMenuId(menu);
			authorization.setRoleId(role);
			authorization.setOperationList("search,add,update,delete,import,export,audit");
			authorizationService.save(authorization);
		}
	}

}
