/**
 * 
 */
package com.basic.framework.platform.menus.daoImpl;

import org.springframework.stereotype.Component;

import com.basic.framework.platform.basic.baseDaoImpl.HibernateEntitryDaoImpl;
import com.basic.framework.platform.menus.IDao.IMenuDao;

/**
 * @author gmc
 *
 */
@Component("menuDao")
public class MenuDaoImpl extends HibernateEntitryDaoImpl implements IMenuDao {

}
