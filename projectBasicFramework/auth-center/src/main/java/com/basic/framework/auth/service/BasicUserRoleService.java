/**
 * 
 */
package com.basic.framework.auth.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.basic.framework.auth.Idao.IBasicUserRoleRepository;
import com.basic.framework.auth.pojo.BasicUserRole;
import com.basic.framework.auth.pojo.PlatformUser;

/**
 * @author gmc
 *
 */
@Service
public class BasicUserRoleService {

	@Autowired
	private IBasicUserRoleRepository basicUserRoleRepository;
	
	public List<BasicUserRole> findByUser(PlatformUser user) throws Exception {
		if(ObjectUtils.isEmpty(user)) {
			throw new Exception("参数错误[user],用户信息不可为空");
		}
		return basicUserRoleRepository.findByUserId(user);
	}
	
	@Transactional
	public BasicUserRole userRoleSave(BasicUserRole userRole) throws Exception{
		if(ObjectUtils.isEmpty(userRole)) {
			throw new Exception("参数错误[userRole],用户角色关系对象为空");
		}
		return basicUserRoleRepository.save(userRole);
	}
}
