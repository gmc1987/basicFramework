/**
 * 
 */
package com.basic.framework.platform.role.roleDaoImpl;

import org.springframework.stereotype.Component;

import com.basic.framework.platform.basic.baseDaoImpl.HibernateEntitryDaoImpl;
import com.basic.framework.platform.role.IRoleDao.IRoleDao;

/**
 * @author gmc
 *
 */
@Component("roleDao")
public class RoleDaoImpl extends HibernateEntitryDaoImpl implements IRoleDao {

}
