/**
 * 
 */
package com.basic.framework.auth.Idao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basic.framework.auth.pojo.BasicMenus;

/**
 * @author gmc
 *
 */
public interface IBasicMenusRepository extends JpaRepository<BasicMenus, Long> {

	public List<BasicMenus> findByParentMenu(BasicMenus parentMenu);
	
	public List<BasicMenus> findByMenuLevel(Integer level);
}
