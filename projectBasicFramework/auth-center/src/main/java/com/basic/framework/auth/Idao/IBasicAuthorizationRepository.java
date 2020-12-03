/**
 * 
 */
package com.basic.framework.auth.Idao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basic.framework.auth.pojo.BasicAuthorization;
import com.basic.framework.auth.pojo.BasicRole;

/**
 * @author gmc
 *
 */
public interface IBasicAuthorizationRepository extends JpaRepository<BasicAuthorization, Long> {

	public List<BasicAuthorization> findByRoleId(BasicRole role);
}
