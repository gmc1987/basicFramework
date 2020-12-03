/**
 * 
 */
package com.basic.framework.platform;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.basic.framework.platform.basic.config.DataSourceConfig;
import com.basic.framework.platform.basic.config.HibernateSessionConfig;
import com.basic.framework.platform.menus.IService.IMenuService;
import com.basic.framework.platform.menus.pojo.Menus;

/**
 * @author gmc
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, HibernateSessionConfig.class})
@SpringBootTest(classes = ServicePlatformApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlatformMenuTests {
	
	@Autowired
	@Qualifier("menuservice")
	private IMenuService menuService;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void menuTestCase() throws Exception {
		
		List<Menus> menuList = new ArrayList<Menus>();
		
		Menus menu = new Menus("system-setting", "系统设置", "menu/system-setting", 0, null, 0, new Date(), 1);
		Menus menu2 = new Menus("system-user", "系统用户管理", "menu/system-user", 0, null, 1, menu, new Date(), 1);
		Menus menu3 = new Menus("system-role", "系统角色管理", "menu/system-role", 0, null, 1, menu, new Date(), 1);
		Menus menu4 = new Menus("system-authorization", "系统用户权限", "menu/system-authorization", 0, null, 1, menu, new Date(), 1);
		Set<Menus> menuSet = new HashSet<Menus>(10);
		menuSet.add(menu2);
		menuSet.add(menu3);
		menuSet.add(menu4);
		menuService.save(menu);
		
		menu.setChildMenus(menuSet);
		menuList.add(menu);
		menuList.add(menu2);
		menuList.add(menu3);
		menuList.add(menu4);
		
		menuService.updateAll(menuList);
		
	}
}
