package com.basic.framework.platform.users.serviceImpl;
/**
 * 
 */

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.basic.framework.platform.authorization.IDao.IUserRoleDao;
import com.basic.framework.platform.authorization.pojo.UserRole;
import com.basic.framework.platform.basic.baseServiceImpl.BasicServiceImpl;
import com.basic.framework.platform.basic.common.utils.DetachedCriteriaTS;
import com.basic.framework.platform.basic.common.utils.PageMode;
import com.basic.framework.platform.role.IRoleDao.IRoleDao;
import com.basic.framework.platform.role.pojo.Role;
import com.basic.framework.platform.users.IDao.IAccountDao;
import com.basic.framework.platform.users.IDao.IUserDao;
import com.basic.framework.platform.users.IService.IUserService;
import com.basic.framework.platform.users.dto.PlatformUserDto;
import com.basic.framework.platform.users.pojo.Account;
import com.basic.framework.platform.users.pojo.PlatformUser;
import com.basic.framework.platform.users.sql.UserSQL;




/**
 * @author gmc
 *
 */
@Service("userService")
public class UserServiceImpl extends BasicServiceImpl<PlatformUser> implements IUserService {

	@Resource
	private IUserDao UserDao;
	
	@Resource
	private IAccountDao AccountDao;
	
	@Resource
	private IRoleDao roleDao;
	
	@Resource
	private IUserRoleDao userRoleDao;
	
	@Override
	public PlatformUser findUserByCode(String code) {
		
		PlatformUser adminUser = null;
		
		DetachedCriteriaTS<PlatformUser> criteria = new DetachedCriteriaTS<PlatformUser>(PlatformUser.class);
		
		if(!StringUtils.isAllEmpty(code)) {
			criteria.add(Restrictions.eq("userCode", code));
			adminUser = UserDao.find(criteria);
		}
		
		return adminUser;
	}

	@Override
	public List<PlatformUser> findAllUser(PlatformUserDto dto) throws Exception {
		List<PlatformUser> list = null;
		
		DetachedCriteriaTS<PlatformUser> criteria = new DetachedCriteriaTS<PlatformUser>(PlatformUser.class);	
		
		if(dto.getAccount() != null) {
			Account account = AccountDao.get(Account.class, dto.getAccount());
			criteria.add(Restrictions.eq("account", account));
		}
		
		if(!StringUtils.isAllEmpty(dto.getUserCode())) {
			criteria.add(Restrictions.eq("userCode", dto.getUserCode()));
		}
		
		if(!StringUtils.isAllEmpty(dto.getUserName())) {
			criteria.add(Restrictions.like("userName", dto.getUserName(), MatchMode.ANYWHERE));
		}
		
		if(dto.getUserStatus() != null) {
			criteria.add(Restrictions.eq("userStatus", dto.getUserStatus()));
		}
		
		if(!StringUtils.isAllEmpty(dto.getSearchKey())) {
			criteria.add(Restrictions.or(Restrictions.like("userCode", dto.getSearchKey(), MatchMode.ANYWHERE), 
										 Restrictions.like("userName", dto.getSearchKey(), MatchMode.ANYWHERE)));
		}
		
		list = UserDao.findAll(criteria);
		
		return list;
	}

	@Override
	public PageMode<PlatformUser> findUserForPages(PlatformUserDto dto, int pageNumber, int pageSize) throws Exception {
		
		DetachedCriteriaTS<PlatformUser> criteria = new DetachedCriteriaTS<PlatformUser>(PlatformUser.class);
		if(dto.getAccount() != null) {
			Account account = AccountDao.get(Account.class, dto.getAccount());
			criteria.add(Restrictions.eq("account", account));
		}
		
		if(!StringUtils.isAllEmpty(dto.getUserCode())) {
			criteria.add(Restrictions.eq("userCode", dto.getUserCode()));
		}
		
		if(!StringUtils.isAllEmpty(dto.getUserName())) {
			criteria.add(Restrictions.like("userName", dto.getUserName(), MatchMode.ANYWHERE));
		}
		
		if(dto.getUserStatus() != null) {
			criteria.add(Restrictions.eq("userStatus", dto.getUserStatus()));
		}
		
		if(!StringUtils.isAllEmpty(dto.getSearchKey())) {
			criteria.add(Restrictions.or(Restrictions.like("userCode", dto.getSearchKey(), MatchMode.ANYWHERE), 
										 Restrictions.like("userName", dto.getSearchKey(), MatchMode.ANYWHERE)));
		}
		
		return UserDao.findForPage(criteria, pageNumber, pageSize);
	}

	@Override
	@Transactional
	public void userEditer(PlatformUser user) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//新增
		if(user != null && user.getUserId() == null) {
			if(user.getAccount() != null) {
				String tmpPwd = user.getAccount().getAccPassword();
				user.getAccount().setAccPassword(encoder.encode(tmpPwd));
				UserDao.saveOrUpdate(user);
			}
		} else { //修改
			PlatformUser old = UserDao.get(PlatformUser.class, user.getUserId());
			BeanUtils.copyProperties(user, old, 
					"userId", "userCode", "createDate", "lastUpdateDate", "createBy", "lastUpdateBy", 
					"account.accountId", "account.accCode", "account.userId", "account.createDate", "account.createBy",
					"account.lastUpdateDate", "account.lastUpdateBy");
			if(user.getAccount() != null && user.getAccount().getAccPassword() != null) {
				if(!encoder.matches(user.getAccount().getAccPassword(), old.getAccount().getAccPassword())) {
					old.getAccount().setAccPassword(encoder.encode(user.getAccount().getAccPassword()));
				}
				old.getAccount().setLastUpdateBy(user.getUserId());
				old.getAccount().setLastUpdateDate(new Date());
			}
			old.setLastUpdateBy(user.getUserId());
			old.setLastUpdateDate(new Date());
			UserDao.saveOrUpdate(old);
		}
		
	}

	@Override
	public PageMode<PlatformUserDto> findSystemUserBySQL(PlatformUserDto dto, int pageNumber, int pageSize) throws Exception {
		
		PageMode<PlatformUserDto> pageMode = null;
		StringBuffer sb = new StringBuffer(500);
		sb.append(UserSQL.FIND_SYSTEM_USER_BYSQL);
		Map<String, String> params = new HashMap<String, String>();
		
		if(!StringUtils.isAllEmpty(dto.getSearchKey())) {
			sb.replace(sb.indexOf("$1"), sb.indexOf("$1")+2, " and (a.usercode like ? or a.username like ? or r.roleName like ?)");
			params.put("usercode", "%" + dto.getSearchKey() + "%");
			params.put("username", "%" + dto.getSearchKey() + "%");
			params.put("roleName", "%" + dto.getSearchKey() + "%");
		} else {
			sb.replace(sb.indexOf("$1"), sb.indexOf("$1")+2, "");
		}
		
		pageMode = UserDao.findBySQL(sb.toString(), params, pageNumber, pageSize, PlatformUserDto.class);
		
		return pageMode;
	}

	@Override
	@Transactional
	public void userRegist(PlatformUser user) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (ObjectUtils.isEmpty(user)) {
			throw new Exception("参数异常:[user]为空");
		}
		String tmpPwd = user.getAccount().getAccPassword();
		user.getAccount().setAccPassword(encoder.encode(tmpPwd));
		UserDao.saveOrUpdate(user);
		
		DetachedCriteriaTS<PlatformUser> criteria = new DetachedCriteriaTS<PlatformUser>(PlatformUser.class);
		DetachedCriteriaTS<Role> roleCriteria = new DetachedCriteriaTS<Role>(Role.class);
		criteria.add(Restrictions.eq("userCode", user.getUserCode()));
		roleCriteria.add(Restrictions.eq("roleCode", "tenant"));
		PlatformUser newUser = UserDao.find(criteria);
		Role role = roleDao.find(roleCriteria);
		UserRole userRole = new UserRole();
		userRole.setUserId(newUser);
		userRole.setRoleId(role);
		userRoleDao.saveOrUpdate(userRole);
	}


}
