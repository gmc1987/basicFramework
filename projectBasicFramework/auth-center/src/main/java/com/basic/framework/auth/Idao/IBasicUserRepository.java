/**
 * 
 */
package com.basic.framework.auth.Idao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basic.framework.auth.pojo.BasicUser;

/**
 * @author gmc
 *
 */
public interface IBasicUserRepository extends JpaRepository<BasicUser, Long> {

	public BasicUser findByUserId(Long userId);
	
	public BasicUser findByUserCode(String userCode);
}
