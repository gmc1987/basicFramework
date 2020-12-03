/**
 * 
 */
package com.basic.framework.platform.basic.baseServiceImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basic.framework.platform.basic.IbaseDao.HibernateEntitryDao;
import com.basic.framework.platform.basic.IbaseService.IBasicService;
import com.basic.framework.platform.basic.common.utils.DetachedCriteriaTS;
import com.basic.framework.platform.basic.common.utils.PageMode;

/**
 * @author gmc
 *
 */
@Service("sideBasicService")
@Transactional
public class BasicServiceImpl<T> implements IBasicService<T> {

	@Autowired
	private HibernateEntitryDao hibernateEntitryDao;
	
	@Override
	@Transactional
	public void save(T entity) {
		hibernateEntitryDao.save(entity);
	}

	@Override
	@Transactional
	public void update(T entity) {
		hibernateEntitryDao.saveOrUpdate(entity);
	}

	@Override
	@Transactional
	public void updateAll(List<T> list) {
		hibernateEntitryDao.saveOrUpdateAll(list);
	}

	@Override
	@Transactional
	public void delete(T entity) {
		hibernateEntitryDao.delete(entity);
	}

	@Override
	@Transactional
	public void deleteAll(List<T> list) {
		hibernateEntitryDao.deleteAll(list);
	}

	@Override
	public PageMode<T> findForList(DetachedCriteriaTS<T> criteria, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return hibernateEntitryDao.findForPage(criteria, pageNumber, pageSize);
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T find(DetachedCriteriaTS<T> criteria) {
		// TODO Auto-generated method stub
		return hibernateEntitryDao.find(criteria);
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> List<T> find(DetachedCriteriaTS<T> detachedCriteria, int maxResult) {
		// TODO Auto-generated method stub
		return hibernateEntitryDao.find(detachedCriteria, maxResult);
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> List<T> findAll(DetachedCriteriaTS<T> detachedCriteria) {
		// TODO Auto-generated method stub
		return hibernateEntitryDao.findAll(detachedCriteria);
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T get(Class<T> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return hibernateEntitryDao.get(entityClass, id);
	}

	@Override
	public PageMode<T> findBySQL(String sql, Map<String, String> params, int pageNumber, int pageSize, Class<T> clazz) {
		// TODO Auto-generated method stub
		return hibernateEntitryDao.findBySQL(sql, params, pageNumber, pageSize, clazz);
	}

	@Override
	public T findObjBySQL(String sql, Map<String, String> params, Class<T> clazz) {
		// TODO Auto-generated method stub
		return hibernateEntitryDao.findObjBySQL(sql, params, clazz);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public PageMode findBySQL(String sql, Map<String, String> params, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return hibernateEntitryDao.findBySQL(sql, params, pageNumber, pageSize);
	}

	@Override
	public Object findObjBySQL(String sql, Map<String, String> params) {
		// TODO Auto-generated method stub
		return hibernateEntitryDao.findObjBySQL(sql, params);
	}

	@Override
	public void executeObjBySql(String sql, Map<String, String> params) {
		hibernateEntitryDao.executeSql(sql, params);
	}

}
