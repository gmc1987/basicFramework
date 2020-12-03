/**
 * 
 */
package com.basic.framework.platform.role.roleServiceImpl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.basic.framework.platform.basic.baseServiceImpl.BasicServiceImpl;
import com.basic.framework.platform.basic.common.utils.DetachedCriteriaTS;
import com.basic.framework.platform.role.IRoleDao.IRoleDao;
import com.basic.framework.platform.role.IRoleService.IRoleService;
import com.basic.framework.platform.role.dto.RoleDto;
import com.basic.framework.platform.role.pojo.Role;

/**
 * @author gmc
 *
 */
@Service("roleService")
public class RoleServiceImpl extends BasicServiceImpl<Role> implements IRoleService {

	@Autowired
	@Qualifier("roleDao")
	private IRoleDao roleDao;

	@Override
	public Role findRoleByCode(String roleCode) throws Exception {
		
		Role role = null;
		
		DetachedCriteriaTS<Role> criteria = new DetachedCriteriaTS<Role>(Role.class);
		
		if(!StringUtils.isAllEmpty(roleCode)) {
			criteria.add(Restrictions.eq("roleCode", roleCode));
			role = roleDao.find(criteria);
		}
		
		return role;
	}

	@Override
	public List<Role> findRoleByKey(RoleDto dto) throws Exception{
		
		DetachedCriteriaTS<Role> criteria = new DetachedCriteriaTS<Role>(Role.class);
		
		if(!ObjectUtils.isEmpty(dto)) {
			
			if ( !StringUtils.isAllEmpty( dto.getKey() ) ) {
				criteria.add(Restrictions.or(Restrictions.like("roleName", dto.getKey(), MatchMode.ANYWHERE), 
											 Restrictions.like("roleCode", dto.getKey(), MatchMode.ANYWHERE)));
			} else {
				
				if(!StringUtils.isAllEmpty(dto.getRoleCode())) {
					criteria.add(Restrictions.like("roleCode", dto.getRoleCode(), MatchMode.ANYWHERE));
				}
				
				if(!StringUtils.isAllEmpty(dto.getRoleCode())) {
					criteria.add(Restrictions.like("roleName", dto.getRoleName(), MatchMode.ANYWHERE));
				}
				
				if(dto.getRoleId() != null) {
					criteria.add(Restrictions.eq("roleId", dto.getRoleId()));
				}
				
				if(dto.getRoleStatus() != null) {
					criteria.add(Restrictions.eq("roleStatus", dto.getRoleStatus()));
				}
				
			}
			
		} 
		
		return roleDao.findAll(criteria);
	}

	@Override
	@Transactional
	public void saveRole(RoleDto dto) throws Exception{
		//roleId不为空时属于编辑、修改操作
		if(dto.getRoleId() != null) {
			
			Role role = roleDao.get(Role.class, dto.getRoleId());
			
			if(!ObjectUtils.isEmpty(role)) {
				role.setRoleCode(dto.getRoleCode());
				role.setRoleName(dto.getRoleName());
				role.setRoleStatus(dto.getRoleStatus());
				role.setLastUpdateBy(dto.getLastUpdateBy());
				role.setLastUpdateDate(dto.getLastUpdateDate());
				roleDao.saveOrUpdate(role);
			}
			
		} else {
			Role role = new Role(dto.getRoleCode(), 
											dto.getRoleName(), 
											dto.getRoleStatus(), 
											dto.getCreateDate(), 
											dto.getCreateBy());
			roleDao.save(role);
		}
		
	}

	@Override
	@Transactional
	public void delRole(RoleDto dto) throws Exception {
		if(dto.getRoleId() != null) {
			Role role = roleDao.get(Role.class, dto.getRoleId());
			if(role != null) {
				roleDao.delete(role);
			}
		}
	}
	
}
