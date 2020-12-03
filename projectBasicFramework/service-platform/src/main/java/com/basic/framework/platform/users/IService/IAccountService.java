/**
 * 
 */
package com.basic.framework.platform.users.IService;

import com.basic.framework.platform.basic.IbaseService.IBasicService;
import com.basic.framework.platform.users.pojo.Account;

/**
 * @author gmc
 *
 */
public interface IAccountService extends IBasicService<Account> {

	public Account findAccountByAccCode(String accountCode);
}
