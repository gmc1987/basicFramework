/**
 * 
 */
package com.basic.framework.platform.basic.IbaseService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.basic.framework.platform.basic.common.utils.DetachedCriteriaTS;
import com.basic.framework.platform.basic.common.utils.PageMode;

/**
 * @author gmc
 * @see 基础service接口
 */
public interface IBasicService<T> {

	/**
	 * 保存
	 * @param entity
	 */
	public void save(T entity);
	
	/**
	 * 更新
	 * @param entity
	 */
	public void update(T entity);
	
	/**
	 * 批量更新
	 * @param list
	 */
	public void updateAll(List<T> list);
	
	/**
	 * 删除
	 * @param entity
	 */
	public void delete(T entity);
	
	/**
	 * 批量删除
	 * @param list
	 */
	public void deleteAll(List<T> list);
	
	/**
	 * 分页查询
	 * @param criteria 参数对象
	 * @param pageNumber 页码
	 * @param pageSize	页记录数
	 * @return
	 */
	public PageMode<T> findForList(DetachedCriteriaTS<T> criteria, int pageNumber, int pageSize);
	
	/**
	 * 查询单个对象
	 * @param criteria
	 * @return
	 */
	@SuppressWarnings("hiding")
	public <T> T find(final DetachedCriteriaTS<T> criteria);
	
	/**
	 * 查询列表
	 * @param detachedCriteria
	 * @param maxResult
	 * @return
	 */
	@SuppressWarnings("hiding")
	public <T> List<T> find(DetachedCriteriaTS<T> detachedCriteria, int maxResult);
	
	/**
	 * 查询所有符合条件记录
	 * @param detachedCriteria
	 * @return
	 */
	@SuppressWarnings("hiding")
	public <T> List<T> findAll(DetachedCriteriaTS<T> detachedCriteria);
	
	/**
	 * 通过id查询
	 * @param entity
	 */
	@SuppressWarnings("hiding")
	public <T> T get(Class<T> entityClass, Serializable id);
	
	/**
	 * 根据sql查询列表
	 * @param sql
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @param clazz
	 * @return
	 */
	public PageMode<T> findBySQL(String sql, Map<String, String> params, int pageNumber, int pageSize, Class<T> clazz);
	
	/**
	 * 根据sql查询单个对象
	 * @param sql
	 * @param params
	 * @param clazz
	 * @return
	 */
	public T findObjBySQL(String sql, Map<String, String> params, Class<T> clazz);
	
	/**
	 * 根据sql查询列表
	 * @param sql
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public PageMode findBySQL(String sql, Map<String, String> params, int pageNumber, int pageSize);
	
	/**
	 * 根据sql查询单个对象
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object findObjBySQL(String sql, Map<String, String> params);
	
	/**
	 * 根据sql操作数据库对象
	 * @param sql
	 * @param params
	 */
	public void executeObjBySql(String sql, Map<String, String> params);
}
