/**
 * 
 */
package com.basic.framework.platform.basic.common.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author gmc
 * @see 缓存操作接口
 * 		1、提供永久的单个对象、list、map、set缓存的增删改查操作
 * 		2、提供具有时效性的单个对象、list、map、set的缓存设置
 */
public interface ICacheService {

	/**
	 * 缓存单个对象，基础类型
	 * @param key
	 * @param obj
	 */
	public void setSingleCache(String key, Object obj);
	
	/**
	 * 在有效时间内缓存单个对象、基础类型
	 * @param key
	 * @param obj
	 * @param timeToLive 有效时间
	 * @param unit 时间单位
	 */
	public void setSingleCacheOnTimeLive(String key, Object obj, long timeToLive, TimeUnit unit);
	
	/**
	 * 缓存列表集合
	 * @param key
	 * @param list
	 */
	public void setListCache(String key, List<Object> list);
	
	/**
	 * 在有效时间内缓存list集合
	 * @param key
	 * @param list
	 * @param timeToLive 有效时间
	 * @param unit 时间单位
	 */
	public void setListCacheOnTimeLive(String key, List<Object> list, long timeToLive, TimeUnit unit);
	
	/**
	 * 缓存map集合
	 * @param key
	 * @param map
	 */
	public void setMapCache(String key, Map<String, Object> map);
	
	/**
	 * 在有效时间内缓存map集合
	 * @param key
	 * @param map
	 * @param timeToLive 有效时间
	 * @param unit 时间单位
	 */
	public void setMapCacehOnTimeLive(String key, Map<String, Object> map, long timeToLive, TimeUnit unit);
	
	/**
	 * 缓存set集合
	 * @param key
	 * @param set
	 */
	public void setSetCache(String key, Set<Object> set);
	
	/**
	 * 在有效时间内缓存set集合
	 * @param key
	 * @param set
	 * @param timeToLive 有效时间
	 * @param unit 时间单位
	 */
	public void setSetCacheOnTimeLive(String key, Set<Object> set, long timeToLive, TimeUnit unit);
	
	/**
	 * 获取单个对象、基础类型缓存值
	 * @param key
	 * @return
	 */
	public Object getSingleCache(String key);
	
	/**
	 * 获取list集合缓存值
	 * @param key
	 * @return
	 */
	public List<Object> getListCache(String key);
	
	/**
	 * 获取map集合缓存值
	 * @param key
	 * @return
	 */
	public Map<Object, Object> getMapCache(String key);
	
	/**
	 * 获取set集合缓存值
	 * @param key
	 * @return
	 */
	public Set<Object> getSetCache(String key);
	
	/**
	 * 删除对应key值的缓存
	 * @param key
	 */
	public long remove(String key);
	
	/**
	 * 删除map中对应的key缓存值
	 * @param key
	 * @return
	 */
	public long removeMapEntry(String key);
	
	
}
