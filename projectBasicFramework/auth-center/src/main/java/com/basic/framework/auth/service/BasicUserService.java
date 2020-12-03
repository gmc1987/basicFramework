/**
 * 
 */
package com.basic.framework.auth.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.basic.framework.auth.Idao.IBasicUserRepository;
import com.basic.framework.auth.pojo.PlatformUser;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * @author gmc
 *
 */
@Service("basicUserService")
public class BasicUserService {

	private static final Logger log = LoggerFactory.getLogger(BasicUserService.class);
	
	@Autowired
	private IBasicUserRepository iBasicUserRepository;
	
	public PlatformUser findUserByCode(String userCode) throws Exception {
		
		if(StringUtils.isEmpty(userCode)) {
			log.error("参数错误[userCode]为空");
			throw new Exception("参数有误，usercode不可为空！");
		}
		
		return iBasicUserRepository.findByUserCode(userCode);
	}
	
	@Transactional
	public PlatformUser userSave(PlatformUser user) throws Exception{
		if(ObjectUtils.isEmpty(user)) {
			log.error("参数错误[user]为空");
			throw new Exception("参数错误，user不可为空");
		}
		return iBasicUserRepository.save(user);
	}
}
