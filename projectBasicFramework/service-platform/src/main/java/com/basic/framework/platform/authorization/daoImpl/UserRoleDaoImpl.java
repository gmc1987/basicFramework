/**
 * 
 */
package com.basic.framework.platform.authorization.daoImpl;

import org.springframework.stereotype.Component;

import com.basic.framework.platform.authorization.IDao.IUserRoleDao;
import com.basic.framework.platform.basic.baseDaoImpl.HibernateEntitryDaoImpl;

/**
 * @author gmc
 *
 */
@Component("userRoleDao")
public class UserRoleDaoImpl extends HibernateEntitryDaoImpl implements IUserRoleDao {

}
