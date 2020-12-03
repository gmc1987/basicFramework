/**
 * 
 */
package com.basic.framework.platform.authorization.daoImpl;

import org.springframework.stereotype.Component;

import com.basic.framework.platform.authorization.IDao.IAuthorizationDao;
import com.basic.framework.platform.basic.baseDaoImpl.HibernateEntitryDaoImpl;

/**
 * @author gmc
 *
 */
@Component("authorizationDao")
public class AuthorizationDaoImpl extends HibernateEntitryDaoImpl implements IAuthorizationDao {
	
	
}
