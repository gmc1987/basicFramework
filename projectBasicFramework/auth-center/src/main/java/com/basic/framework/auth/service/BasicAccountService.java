/**
 * 
 */
package com.basic.framework.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.basic.framework.auth.Idao.IBasicAccountRepository;
import com.basic.framework.auth.pojo.Account;
import com.basic.framework.auth.pojo.PlatformUser;

/**
 * @author gmc
 *
 */

@Service
public class BasicAccountService {

	@Autowired
	private IBasicAccountRepository basicAccountRepository;
	
	public Account findByUser(PlatformUser user) throws Exception{
		if(ObjectUtils.isEmpty(user)) {
			throw new Exception("参数错误[user]，用户信息为空");
		}
		return basicAccountRepository.findByAccountId(user.getAccount().getAccountId());
	}
}
