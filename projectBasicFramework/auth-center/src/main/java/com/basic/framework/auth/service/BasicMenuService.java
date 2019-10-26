/**
 * 
 */
package com.basic.framework.auth.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.basic.framework.auth.Idao.IBasicMenusRepository;
import com.basic.framework.auth.pojo.BasicMenus;

/**
 * @author gmc
 *
 */

@Service
public class BasicMenuService {

	@Autowired
	private IBasicMenusRepository basicMenusRepository;
	
	public List<BasicMenus> findByParent(BasicMenus parent) throws Exception{
		
		if(ObjectUtils.isEmpty(parent)) {
			throw new Exception("参数错误[parent],父级菜单对象为空");
		}
		
		return basicMenusRepository.findByParentMenu(parent);
	}
	
	@Transactional
	public BasicMenus menuSave(BasicMenus menu) throws Exception{
		if(ObjectUtils.isEmpty(menu)) {
			throw new Exception("参数错误[menu],菜单对象为空");
		}
		return basicMenusRepository.save(menu);
	}
}
