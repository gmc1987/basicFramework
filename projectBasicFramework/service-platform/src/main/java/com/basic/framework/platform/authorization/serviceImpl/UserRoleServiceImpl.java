/**
 * 
 */
package com.basic.framework.platform.authorization.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basic.framework.platform.authorization.IDao.IUserRoleDao;
import com.basic.framework.platform.authorization.IService.IUserRoleService;
import com.basic.framework.platform.authorization.dto.RoleUserDto;
import com.basic.framework.platform.authorization.pojo.UserRole;
import com.basic.framework.platform.basic.baseServiceImpl.BasicServiceImpl;
import com.basic.framework.platform.basic.common.utils.DetachedCriteriaTS;
import com.basic.framework.platform.role.IRoleService.IRoleService;
import com.basic.framework.platform.role.pojo.Role;
import com.basic.framework.platform.users.IService.IUserService;
import com.basic.framework.platform.users.pojo.PlatformUser;


/**
 * @author gmc
 *
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends BasicServiceImpl<UserRole> implements IUserRoleService {
	
	@Autowired
	@Qualifier("userRoleDao")
	private IUserRoleDao userRoleDao;
	
	@Qualifier("userService")
	@Autowired
	private IUserService UserService;
	
	@Qualifier("roleService")
	@Autowired
	private IRoleService roleService;

	@Override
	public List<UserRole> findUserRoleByUserId(Long userId) {

		List<UserRole> userRoleList = null;
		if(userId > 0) {
			DetachedCriteriaTS<UserRole> criteria = new DetachedCriteriaTS<UserRole>(UserRole.class);
			criteria.getCriteria().createAlias("userId", "user");
			criteria.add(Restrictions.eqOrIsNull("user.userId", userId));
			userRoleList = userRoleDao.findAll(criteria);
		}
		return userRoleList;
	}
	
	@Override
	@Transactional
	public void saveUserRole(List<RoleUserDto> list) throws Exception {
		
		List<UserRole> userRoleList = new ArrayList<UserRole>();
		
		if(list != null && list.size() > 0) {
			
			DetachedCriteriaTS<UserRole> criteria = new DetachedCriteriaTS<UserRole>(UserRole.class);
			criteria.getCriteria().createAlias("userId", "user");
			criteria.add(Restrictions.eqOrIsNull("user.userId", list.get(0).getUserId()));
			
			List<UserRole> delUserRoleList = userRoleDao.findAll(criteria);
			
			if(delUserRoleList != null && delUserRoleList.size() > 0) {
				userRoleDao.deleteAll(delUserRoleList);
			}
			
			for(RoleUserDto dto : list) {
				UserRole userRoleTmp = new UserRole();
				PlatformUser user = UserService.get(PlatformUser.class, dto.getUserId());
				Role role = roleService.get(Role.class, dto.getRoleId());
				userRoleTmp.setRoleId(role);
				userRoleTmp.setUserId(user);
				userRoleList.add(userRoleTmp);
			}
			
			userRoleDao.saveOrUpdateAll(userRoleList);
		}
	}
}
