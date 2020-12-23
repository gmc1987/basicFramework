/**
 * 
 */
package com.basic.framework.auth.Idao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basic.framework.auth.pojo.BasicAccount;

/**
 * @author gmc
 *
 */
public interface IBasicAccountRepository extends JpaRepository<BasicAccount, Long> {

	public BasicAccount findByAccountId(Long account);
}
