/**
 * 
 */
package com.basic.framework.platform.basic.IbaseDao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.basic.framework.platform.basic.common.utils.DetachedCriteriaTS;
import com.basic.framework.platform.basic.common.utils.PageMode;

/**
 * @author gmc
 * @see 数据库实体操作实现类，提供通用查询，新增，修改，删除接口
 */
public interface HibernateEntitryDao {

	/**
	 * 查询单个实体
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> entityClass, Serializable id);
	
	/**
	 * 返回单条数据
	 * 
	 * @see DetachedCriteriaWrapper
	 */
	<T> T find(final DetachedCriteriaTS<T> criteria);
	
	/**
	 * 分页查询
	 * @param criteria
	 * @param first 第一条记录
	 * @param maxResult 最后一条记录
	 * @return
	 */
	public <T> List<T> find(DetachedCriteriaTS<T> detachedCriteria, int first, int maxResult);
	
	/**
	 * 查询总数
	 * @param criteria
	 * @param maxResult
	 * @return
	 */
	public <T> List<T> find(DetachedCriteriaTS<T> detachedCriteria, int maxResult);
	
	/**
	 * 查询所有符合条件记录
	 * @param detachedCriteria
	 * @return
	 */
	public <T> List<T> findAll(DetachedCriteriaTS<T> detachedCriteria);
	
	/**
	 * 保存一个对象
	 * @param obj
	 */
	public void save(Object entity);
	
	/**
	 * 更新指定对象
	 * @param entity 对象
	 */
	public void saveOrUpdate(Object entity);
	
	/**
	 * 更新指定集合对象
	 * @param entities	对象集合
	 */
	public <T> void saveOrUpdateAll(Collection<T> entities) ;
	
	/**
	 * 删除指定对象
	 * @param entity 实体对象
	 */
	public void delete(Object entity);
	
	/**
	 * 删除指定对象集合
	 * @param entities	对象集合
	 */
	public <T> void deleteAll(Collection<T> entities);
	
	/**
	 * 查询总记录数
	 * @param criteria
	 * @return
	 */
	public <T> int count(final DetachedCriteriaTS<T> criteria);
	
	/**
	 * 分页查询
	 * @param criteria 参数对象
	 * @param pageNumber 开始记录行
	 * @param pageSize	每页记录数
	 * @return
	 */
	public <T> PageMode<T> findForPage(DetachedCriteriaTS<T> criteria, int pageNumber, int pageSize);
	
	/**
	 * 根据sql查询数据
	 * @param sql 查询语句
	 * @param params 查询参数
	 * @param pageNumber 开始记录行
	 * @param pageSize 每页记录数
	 * @param clazz	实体类型
	 * @return
	 */
	public <T> PageMode<T> findBySQL(String sql, Map<String, String> params, int pageNumber, int pageSize, Class<T> clazz);
	
	/**
	 * 根据sql查询对象
	 * @param sql 查询语句
	 * @param params 查询参数
	 * @param clazz 实体类型
	 * @return
	 */
	public <T> T findObjBySQL(String sql, Map<String, String> params, Class<T> clazz);
	
	/**
	 * 根据sql查询数据集合
	 * @param sql 查询语句
	 * @param params 查询参数
	 * @param pageNumber 开始记录行
	 * @param pageSize 每页记录数
	 * @return
	 */
	public <T> PageMode<T> findBySQL(String sql, Map<String, String> params, int pageNumber, int pageSize);
	
	/**
	 * 根据sql查询对象
	 * @param sql 查询语句
	 * @param params 查询参数
	 * @return
	 */
	public Object findObjBySQL(String sql, Map<String, String> params);
	
	/**
	 * 根据sql操作数据库
	 * @param sql 操作数据库sql(可以是insert/update/delete)
	 * @param params 参数列表(参数名必须与表字段名相同)
	 */
	public void executeSql(String sql, Map<String, String> params);
	
}
