/**
 * 
 */
package com.basic.framework.platform.users.daoImpl;

import org.springframework.stereotype.Component;

import com.basic.framework.platform.basic.baseDaoImpl.HibernateEntitryDaoImpl;
import com.basic.framework.platform.users.IDao.IUserDao;

/**
 * @author gmc
 *
 */
@Component
public class UserDaoImpl extends HibernateEntitryDaoImpl implements IUserDao {

}
