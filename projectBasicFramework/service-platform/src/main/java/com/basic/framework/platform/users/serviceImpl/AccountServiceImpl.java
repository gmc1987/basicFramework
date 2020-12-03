/**
 * 
 */
package com.basic.framework.platform.users.serviceImpl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.basic.framework.platform.basic.baseServiceImpl.BasicServiceImpl;
import com.basic.framework.platform.basic.common.utils.DetachedCriteriaTS;
import com.basic.framework.platform.users.IDao.IAccountDao;
import com.basic.framework.platform.users.IService.IAccountService;
import com.basic.framework.platform.users.pojo.Account;


/**
 * @author gmc
 *
 */
@Service("accountService")
public class AccountServiceImpl extends BasicServiceImpl<Account> implements IAccountService {
	
	@Resource
	private IAccountDao accountDao;

	@Override
	public Account findAccountByAccCode(String accountCode) {
		
		Account account = null;
		DetachedCriteriaTS<Account> criteria = new DetachedCriteriaTS<Account>(Account.class);
		
		if(!StringUtils.isAllEmpty(accountCode)) {
			criteria.add(Restrictions.eq("accCode", accountCode));
			account = accountDao.find(criteria);
		}
		
		return account;
	}

	

}
