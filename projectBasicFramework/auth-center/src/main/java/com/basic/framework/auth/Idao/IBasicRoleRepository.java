/**
 * 
 */
package com.basic.framework.auth.Idao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basic.framework.auth.pojo.BasicRole;

/**
 * @author gmc
 *
 */
public interface IBasicRoleRepository extends JpaRepository<BasicRole, Long> {

	public BasicRole findByRoleId(Long roleId);
}
