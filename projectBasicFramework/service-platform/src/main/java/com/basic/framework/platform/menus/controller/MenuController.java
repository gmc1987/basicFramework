/**
 * 
 */
package com.basic.framework.platform.menus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basic.framework.platform.basic.SideException.CustException;
import com.basic.framework.platform.basic.common.josn.JsonTools;
import com.basic.framework.platform.menus.IService.IMenuService;
import com.basic.framework.platform.menus.dto.MenuDto;
import com.basic.framework.platform.menus.pojo.Menus;
import com.basic.framework.platform.users.IService.IUserService;
import com.basic.framework.platform.users.pojo.PlatformUser;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author gmc
 * 
 */

@RestController
@RequestMapping("/menu")
public class MenuController {
	
	@Qualifier("menuservice")
	@Autowired
	private IMenuService Menuservice;
	
	@Qualifier("userService")
	@Autowired
	private IUserService userService;
	
	@GetMapping("search")
	public Map<String, Object> findMenus(MenuDto dto){
		Map<String, Object> result = new HashMap<String, Object>();
		List<Menus> mode;
		try {
			mode = Menuservice.findMenuByKey(dto);
			result.put("success", true);
			result.put("data", JsonTools.obj2Json(mode));
			return result;
		} catch (CustException e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("data", null);
			return result;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("data", null);
			return result;
		}
		
	}
	
	@PutMapping("editMenuService")
	public Map<String, Object> menuEdit(MenuDto dto){
		Map<String, Object> result = new HashMap<String, Object>();
		List<Menus> mode;
		try {
			PlatformUser user = userService.findUserByCode((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			if(user != null) {
				dto.setCurrentUser(String.valueOf(user.getUserId()));
			}
			Menuservice.editMenuByDto(dto);
			
			dto.setKey("");
			dto.setMenuCode("");
			dto.setMenuName("");
			dto.setMenuPath("");
			if(dto.getParentMenu() != null) {
				dto.setParentId(String.valueOf(dto.getParentMenu()));
			}
			
			mode = Menuservice.findMenuByKey(dto);
			result.put("success", true);
			result.put("data", JsonTools.obj2Json(mode));
			return result;
			
		} catch (CustException e) {
			result.put("success", false);
			result.put("data", null);
			return result;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("data", null);
			return result;
		}
	}
	
	@DeleteMapping("delMenuService")
	public Map<String, Object> menuDelete(MenuDto dto){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Menuservice.delMenuByDto(dto);
			result.put("success", true);
			result.put("msg", "删除成功");
		} catch (CustException e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "删除失败");
		}
		return result;
	}

}
