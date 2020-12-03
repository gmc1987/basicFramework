/**
 * 
 */
package com.basic.framework.auth.Idao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basic.framework.auth.pojo.BasicUserRole;
import com.basic.framework.auth.pojo.PlatformUser;


/**
 * @author gmc
 *
 */
public interface IBasicUserRoleRepository extends JpaRepository<BasicUserRole, Long> {

	public List<BasicUserRole> findByUserId(PlatformUser userId);
}
