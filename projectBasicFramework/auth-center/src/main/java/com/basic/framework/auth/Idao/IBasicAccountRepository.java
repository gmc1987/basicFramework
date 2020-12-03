/**
 * 
 */
package com.basic.framework.auth.Idao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basic.framework.auth.pojo.Account;

/**
 * @author gmc
 *
 */
public interface IBasicAccountRepository extends JpaRepository<Account, Long> {

	public Account findByAccountId(Long account);
}
