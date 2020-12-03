/**
 * 
 */
package com.basic.framework.platform.authorization.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.basic.framework.platform.authorization.IDao.IAuthorizationDao;
import com.basic.framework.platform.authorization.IService.IAuthorizationService;
import com.basic.framework.platform.authorization.dto.AuthorizationDto;
import com.basic.framework.platform.authorization.pojo.Authorization;
import com.basic.framework.platform.basic.baseServiceImpl.BasicServiceImpl;
import com.basic.framework.platform.basic.common.utils.DetachedCriteriaTS;
import com.basic.framework.platform.menus.IService.IMenuService;
import com.basic.framework.platform.menus.pojo.Menus;
import com.basic.framework.platform.role.IRoleService.IRoleService;
import com.basic.framework.platform.role.pojo.Role;

/**
 * @author gmc
 *
 */
@Service("authorizationService")
public class AuthorizationServiceImpl extends BasicServiceImpl<Authorization> implements IAuthorizationService {
	
	@Autowired
	@Qualifier("authorizationDao")
	private IAuthorizationDao authorizationDao;
	
	@Autowired
	@Qualifier("roleService")
	private IRoleService roleService;
	
	@Qualifier("menuservice")
	@Autowired
	private IMenuService MenuService;

	@Override
	public List<Authorization> findAuthorizationByRole(String roleCode) throws Exception {
		List<Authorization> authorizations = null;
		Role role = roleService.findRoleByCode(roleCode);
		if(role != null) {
			DetachedCriteriaTS<Authorization> criteria = new DetachedCriteriaTS<Authorization>(Authorization.class);
			criteria.getCriteria().createAlias("menuId", "menu");
			criteria.add(Restrictions.eqOrIsNull("roleId", role));
			criteria.addOrder(Order.asc("menu.menuSort"));
			criteria.addOrder(Order.asc("menu.isParent"));
			authorizations = authorizationDao.findAll(criteria);
		}
		return authorizations;
	}

	@Override
	public List<Authorization> findParentAuthorizationByRole(String roleCode) throws Exception {
		DetachedCriteriaTS<Authorization> criteria = new DetachedCriteriaTS<Authorization>(Authorization.class);
		if(StringUtils.isEmpty(roleCode)) {
			return null;
		}
		criteria.getCriteria().createAlias("roleId", "role");
		criteria.getCriteria().createAlias("menuId", "menu");
		
		criteria.add(Restrictions.eq("role.roleCode", roleCode));
		criteria.add(Restrictions.eq("menu.isParent", 0));
		
		criteria.addOrder(Order.desc("menu.menuSort"));
		
		return authorizationDao.findAll(criteria);
		
	}

	@Override
	@Cache(region="authorization", usage=CacheConcurrencyStrategy.READ_ONLY)
	public List<Authorization> findAuthorizationByRoleAndMenu(AuthorizationDto dto) throws Exception {
		
		List<Authorization> authorizations = null;
		Role role = null;
		Menus menu = null;
		
		if (ObjectUtils.isEmpty(dto)) {
			throw new Exception("参数有误！");
		} else {
			role = roleService.get(Role.class, dto.getRoleId());
			menu = MenuService.get(Menus.class, dto.getMenuId());
			
			if(role != null && menu != null) {
				DetachedCriteriaTS<Authorization> criteria = new DetachedCriteriaTS<Authorization>(Authorization.class);
				criteria.add(Restrictions.eq("roleId", role));
				criteria.add(Restrictions.eq("menuId", menu));
				authorizations = authorizationDao.findAll(criteria);
			}
			
		}
		
		return authorizations;
	}

	@Override
	@Transactional
	public void saveAllAuthorizations(List<AuthorizationDto> list) throws Exception {
		List<Authorization> addList = new ArrayList<Authorization>();
		List<Authorization> delList = new ArrayList<Authorization>();
		//过滤新增与删除的数据
		for(AuthorizationDto authorization : list) {
			if(StringUtils.isEmpty(authorization.getOperationList()) && authorization.getAuthorizationId() != null) { //删除
				Authorization tmp = authorizationDao.get(Authorization.class, authorization.getAuthorizationId());
				delList.add(tmp);
			} else { //新增
				if(authorization.getAuthorizationId() == null) {
					Role role = roleService.get(Role.class, authorization.getRoleId());
					Menus menu = MenuService.get(Menus.class, authorization.getMenuId());
					Authorization tmp = new Authorization();
					tmp.setMenuId(menu);
					tmp.setRoleId(role);
					tmp.setOperationList(authorization.getOperationList());
					addList.add(tmp);
				} else { //修改
					Authorization tmp = authorizationDao.get(Authorization.class, authorization.getAuthorizationId());
					tmp.setOperationList(authorization.getOperationList());
					addList.add(tmp);
				}
			}
		}
		//执行删除数据
		if(delList.size() > 0) {
			authorizationDao.deleteAll(delList);
		}
		//执行新增/更新操作
		if(addList.size() > 0) {
			authorizationDao.saveOrUpdateAll(addList);
		}
		
	}
}
