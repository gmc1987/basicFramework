/**
 * 
 */
package com.basic.framework.auth.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.basic.framework.auth.Idao.IBasicAuthorizationRepository;
import com.basic.framework.auth.pojo.BasicAuthorization;
import com.basic.framework.auth.pojo.BasicRole;

/**
 * @author gmc
 *
 */

@Service
public class BasicAuthorizationService {

	@Autowired
	private IBasicAuthorizationRepository basicAuthorizationRepository;
	
	public List<BasicAuthorization> findByRole(BasicRole role) throws Exception{
		if(ObjectUtils.isEmpty(role)) {
			throw new Exception("参数错误[role],角色参数为空");
		}
		return basicAuthorizationRepository.findByRole(role);
	}
	
	@Transactional
	public BasicAuthorization authorizationSave(BasicAuthorization authorization) throws Exception{
		if(ObjectUtils.isEmpty(authorization)) {
			throw new Exception("参数错误[authorization],授权对象参数为空");
		}
		return basicAuthorizationRepository.save(authorization);
	}
}
