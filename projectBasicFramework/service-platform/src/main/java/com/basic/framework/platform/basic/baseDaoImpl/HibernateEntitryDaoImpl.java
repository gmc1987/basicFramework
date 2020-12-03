/**
 * 底层hibernate数据库操作接口实现
 */
package com.basic.framework.platform.basic.baseDaoImpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.basic.framework.platform.basic.IbaseDao.HibernateEntitryDao;
import com.basic.framework.platform.basic.common.utils.DetachedCriteriaTS;
import com.basic.framework.platform.basic.common.utils.PageMode;

/**
 * @author gmc
 */
@Component
@Primary
public class HibernateEntitryDaoImpl extends HibernateDaoSupport implements HibernateEntitryDao {
	
	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(HibernateEntitryDaoImpl.class);
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Resource(name="sessionFactory")
	public void setSupperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	
	protected Session getCurrentSession()  {
		return this.currentSession();
	}
	
	/**
	 * 查询单个实体
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> entityClass, Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 分页查询
	 * 
	 * @param criteria
	 * @param first
	 *            第一条记录
	 * @param maxResult
	 *            最后一条记录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> find(DetachedCriteriaTS<T> detachedCriteria, int first, int maxResult) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getCurrentSession());
		if (first > 0 && maxResult != 0) {
			criteria.setFirstResult(first / maxResult + 1);
		}
		if (maxResult > 0) {
			criteria.setMaxResults(maxResult);
		}
		return criteria.list();
	}

	/**
	 * 查询总数
	 * 
	 * @param criteria
	 * @param maxResult
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> find(DetachedCriteriaTS<T> detachedCriteria, int maxResult) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getCurrentSession());
		if (maxResult > 0) {
			criteria.setMaxResults(maxResult);
		}
		return criteria.list();
	}

	/**
	 * 保存一个对象
	 * 
	 * @param obj
	 */
	public void save(Object entity) {
		super.getHibernateTemplate().save(entity);
	}
	
	/**
	 * 更新指定对象
	 * 
	 * @param entity
	 *            对象
	 */
	//@Transactional
	public void saveOrUpdate(Object entity) {
		super.getHibernateTemplate().saveOrUpdate(getCurrentSession().merge(entity));
	}

	/**
	 * 更新指定集合对象
	 * 
	 * @param entities
	 *            对象集合
	 */
	//@Transactional
	public <T> void saveOrUpdateAll(Collection<T> entities) {
		if (entities != null && entities.size() > 0) {
			for (T t : entities) {
				getHibernateTemplate().saveOrUpdate(getCurrentSession().merge(t));
			}
		}
	}

	/**
	 * 删除指定对象
	 * 
	 * @param entity
	 *            实体对象
	 */
	//@Transactional
	public void delete(Object entity) {
		getHibernateTemplate().delete(getCurrentSession().merge(entity));
	}

	/**
	 * 删除指定对象集合
	 * 
	 * @param entities
	 *            对象集合
	 */
	//@Transactional
	public <T> void deleteAll(Collection<T> entities) {
		if (entities != null && entities.size() > 0) {
			for(Object entity : entities) {
				getHibernateTemplate().delete(getCurrentSession().merge(entity));
			}
			
		}
	}

	/**
	 * 查询总记录数
	 * 
	 * @param criteria
	 * @return
	 */
	public <T> int count(final DetachedCriteriaTS<T> criteria) {
		final Criteria c = criteria.getExecutableCriteria(getCurrentSession());
		Object obj = c.setProjection(Projections.rowCount()).uniqueResult();
		if (LOG.isDebugEnabled()) {
			LOG.debug(obj.getClass().getName() + "; count : " + obj.toString());
		}
		if (Long.class.isAssignableFrom(obj.getClass())) {
			long count = (Long) obj;
			return (int) (count);
		} else if (Integer.class.isAssignableFrom(obj.getClass())) {
			return (Integer) obj;
		}
		throw new ClassCastException(obj.getClass().getName());
	}

	@Override
	public <T> PageMode<T> findForPage(DetachedCriteriaTS<T> criteria, int pageNumber, int pageSize) {
		return findForPage(criteria, pageNumber, pageSize, null, null, false);
	}

	private <T> PageMode<T> findForPage(DetachedCriteriaTS<T> criteria, int pageNumber, int pageSize,
			CacheMode cacheMode, String cacheRegion, boolean cacheable) {

		if (pageNumber < 0 || pageSize <= 0) {
			throw new IllegalArgumentException("参数错误！");
		}
		
		DetachedCriteriaTS<T> criteria2 = (DetachedCriteriaTS<T>) SerializationUtils.clone(criteria);
		int count = count(criteria);

		if (count <= 0) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("count <= 0");
			}
			return new PageMode<T>();
		}
		List<T> list = find(criteria2, (pageNumber-1) * pageSize, pageSize);

		if (list == null) {
			return new PageMode<T>();
		}

		return new PageMode<T>(list, pageNumber, pageSize, count);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T find(DetachedCriteriaTS<T> criteria) {
		Criteria criteriaTS = criteria.getExecutableCriteria(getCurrentSession());
		return (T) criteriaTS.setMaxResults(1).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(DetachedCriteriaTS<T> detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getCurrentSession());
		return criteria.list();
	}

	@Override
	public <T> PageMode<T> findBySQL(String sql, Map<String, String> params, int pageNumber, int pageSize,
			Class<T> clazz) {
		return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<PageMode<T>>() {
			@Override
			public PageMode<T> doInHibernate(Session session) throws HibernateException {
				List<T> list = null;
				int count  = 0;
				PageMode<T> pageMode = null;
				
				//增加自定义实体转换
				@SuppressWarnings("unchecked")
				NativeQuery<T> query = getCurrentSession().createNativeQuery(sql).setResultSetMapping(clazz.getSimpleName());//setResultSetMapping指定数据集映射的类
				
				if(params != null && params.size() > 0) {
					int i = 1;
					for(String key : params.keySet()) {
						query.setParameter(i, params.get(key));
						i++;
					}
				}
				query.setFirstResult((pageNumber - 1) * pageSize);
				query.setMaxResults(pageSize);
				
				list = query.getResultList();
				count = findCountBySQL(sql, params);
				if(list != null && list.size() > 0) {
					pageMode = new PageMode<>(list, pageNumber, pageSize, count);
				}
				return pageMode;
			}
		});
	}

	@Override
	public <T> T findObjBySQL(String sql, Map<String, String> params, Class<T> clazz) {
		return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				@SuppressWarnings("unchecked")
				NativeQuery<T> query = getCurrentSession().createNativeQuery(sql).setResultSetMapping(clazz.getSimpleName());//setResultSetMapping指定数据集映射的类;
				if(params != null && params.size() > 0) {
					int i = 1;
					for(String key : params.keySet()) {
						query.setParameter(i, params.get(key));
						i++;
					}
				}
				return query.uniqueResult();
			}
		});
		
	}
	
	@SuppressWarnings("rawtypes")
	private int findCountBySQL(String sql, Map<String, String> params) {
		return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				Object myCount = null;
				StringBuffer sb = new StringBuffer(1000);
				sb.append("select count(1) from (");
				if(StringUtils.isNotEmpty(sql)) {
					sb.append(sql);
				}
				sb.append(") t ");
				
				NativeQuery query = getCurrentSession().createNativeQuery(sb.toString());
				
				if(params != null && params.size() > 0) {
					int i = 1;
					for(String key : params.keySet()) {
						query.setParameter(i, params.get(key));
						i++;
					}
				}
				
				if(query.getResultList() != null && query.getResultList().size() > 0) {
					myCount = query.getResultList().get(0);
				}
				
				if (Long.class.isAssignableFrom(myCount.getClass())) {
					long count = (Long) myCount;
					return (int) (count);
				} else if (Integer.class.isAssignableFrom(myCount.getClass())) {
					return (Integer) myCount;
				} else if (BigInteger.class.isAssignableFrom(myCount.getClass())) {
					String temp = String.valueOf(myCount);
					return Integer.parseInt(temp);
				} else if (BigDecimal.class.isAssignableFrom(myCount.getClass())) {
					String temp = String.valueOf(myCount);
					return Integer.parseInt(temp);
				}
				throw new ClassCastException(myCount.getClass().getName());
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T> PageMode<T> findBySQL(String sql, Map<String, String> params, int pageNumber, int pageSize) {
		return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<PageMode>() {
			@Override
			public PageMode<T> doInHibernate(Session session) throws HibernateException {
				List list = null;
				int count  = 0;
				PageMode<T> pageMode = null;
				NativeQuery query = getCurrentSession().createNativeQuery(sql);
				
				if(params != null && params.size() > 0) {
					int i = 1;
					for(String key : params.keySet()) {
						query.setParameter(i, params.get(key));
						i++;
					}
				}
				query.setFirstResult((pageNumber - 1) * pageSize);
				query.setMaxResults(pageSize);
				
				list = query.getResultList();
				count = findCountBySQL(sql, params);
				if(list != null && list.size() > 0) {
					pageMode = new PageMode<T>(list, pageNumber, pageSize, count);
				}
				return pageMode;
			}
		});
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object findObjBySQL(String sql, Map<String, String> params) {
		return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				NativeQuery query=session.createNativeQuery(sql);
				if(params != null && params.size() > 0) {
					int i = 1;
					for(String key : params.keySet()) {
						query.setParameter(i, params.get(key));
						i++;
					}
				}
                return query.uniqueResult();
			}
		});
	}

	@Override
	public void executeSql(String sql, Map<String, String> params) {
		getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				@SuppressWarnings("rawtypes")
				NativeQuery query=session.createNativeQuery(sql);
				if(params != null && params.size() > 0) {
					int i = 1;
					for(String key : params.keySet()) {
						query.setParameter(i, params.get(key));
						i++;
					}
				}
                return query.executeUpdate();
			}
		});
	}

}
