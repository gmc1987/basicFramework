/**
 * 
 */
package com.basic.framework.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.basic.framework.auth.Idao.IBasicRoleRepository;
import com.basic.framework.auth.pojo.BasicRole;

/**
 * @author gmc
 *
 */

@Service
public class BasicRoleService {

	@Autowired
	private IBasicRoleRepository basicRoleRepository;
	
	public BasicRole findRoleById(Long id) throws Exception{
		if(ObjectUtils.isEmpty(id)) {
			throw new Exception("参数有误[id]，角色id不能为空");
		}
		return basicRoleRepository.findByRoleId(id);
	}
	
	public BasicRole saveRole(BasicRole role) throws Exception{
		if(ObjectUtils.isEmpty(role)) {
			throw new Exception("参数有误[id], 角色信息不能为空");
		}
		return basicRoleRepository.save(role);
	}
	
}
