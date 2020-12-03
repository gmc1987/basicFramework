/**
 * 
 */
package com.basic.framework.auth.Idao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basic.framework.auth.pojo.PlatformUser;

/**
 * @author gmc
 *
 */
public interface IBasicUserRepository extends JpaRepository<PlatformUser, Long> {

	public PlatformUser findByUserId(Long userId);
	
	public PlatformUser findByUserCode(String userCode);
}
