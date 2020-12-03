/**
 * 
 */
package com.basic.framework.platform.basic.common.redis;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author gmc
 * @see redis操作实现类
 */

@Service("cacheService")
public class CacheServiceImpl implements ICacheService {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void setSingleCache(String key, Object obj) {
		redisTemplate.opsForValue().set(key, obj);
	}

	@Override
	public void setSingleCacheOnTimeLive(String key, Object obj, long timeToLive, TimeUnit unit) {
		redisTemplate.opsForValue().set(key, obj, timeToLive, TimeUnit.MILLISECONDS);
	}

	@Override
	public void setListCache(String key, List<Object> list) {
		ListOperations<String, Object> listOperations = redisTemplate.opsForList();
		if(list != null && list.size() > 0) {
			listOperations.rightPushAll(key, list);
		}
	}

	@Override
	public void setListCacheOnTimeLive(String key, List<Object> list, long timeToLive, TimeUnit unit) {
		ListOperations<String, Object> listOperations = redisTemplate.opsForList();
		if(list != null && list.size() > 0) {
			listOperations.rightPushAll(key, list);
			redisTemplate.expire(key, timeToLive, unit);
		}
	}

	@Override
	public void setMapCache(String key, Map<String, Object> map) {
		HashOperations<String, Object, Object> mapOperations = redisTemplate.opsForHash();
		if(map != null && map.size() > 0) {
			mapOperations.putAll(key, map);
		}
	}

	@Override
	public void setMapCacehOnTimeLive(String key, Map<String, Object> map, long timeToLive, TimeUnit unit) {
		HashOperations<String, Object, Object> mapOperations = redisTemplate.opsForHash();
		if(map != null && map.size() > 0) {
			mapOperations.putAll(key, map);
			redisTemplate.expire(key, timeToLive, unit);
		}
	}

	@Override
	public void setSetCache(String key, Set<Object> set) {
		BoundSetOperations<String, Object> setOperations = redisTemplate.boundSetOps(key);
		if(set != null && set.size() > 0) {
			for(Object obj : set) {
				setOperations.add(obj);
			}
		}
	}

	@Override
	public void setSetCacheOnTimeLive(String key, Set<Object> set, long timeToLive, TimeUnit unit) {
		BoundSetOperations<String, Object> setOperations = redisTemplate.boundSetOps(key);
		if(set != null && set.size() > 0) {
			for(Object obj : set) {
				setOperations.add(obj);
			}
			setOperations.expire(timeToLive, unit);
		}
	}

	@Override
	public Object getSingleCache(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public List<Object> getListCache(String key) {
		List<Object> list = redisTemplate.opsForList().range(key, 0, -1);
		if(list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public Map<Object, Object> getMapCache(String key) {
		HashOperations<String, Object, Object> mapOperations = redisTemplate.opsForHash();
		return mapOperations.entries(key);
	}

	@Override
	public Set<Object> getSetCache(String key) {
		Set<Object> set = new HashSet<Object>();
		BoundSetOperations<String, Object> boundSetOperations = redisTemplate.boundSetOps(key);
		for(int i = 0; i < boundSetOperations.size(); i++) {
			set.add(boundSetOperations.pop());
		}
		return set;
	}

	@Override
	public long remove(String key) {
		
		Object flag = redisTemplate.execute(new RedisCallback<Object>(){

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.del(key.getBytes());
			}
			
		});
		
		if(flag != null) {
			return (long) flag;
		} else {
			return 0l;
		}
		
	}

	@Override
	public long removeMapEntry(String key) {
		redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate.opsForHash().delete(key);
	}

}
