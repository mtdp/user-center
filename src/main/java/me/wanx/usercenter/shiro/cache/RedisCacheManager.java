package me.wanx.usercenter.shiro.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import me.wanx.common.core.persistence.RedisManager;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用redis实现缓存shiro权限
* @ClassName: RedisCacheManager 
* @Description: TODO 
* @author gqwang
* @date 2015年8月6日 上午11:13:29 
*
 */
public class RedisCacheManager implements CacheManager {
	private static final Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);
	
	private RedisManager redisManager;
	
	/** 保存redisCache实例 **/
	private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();
	
	@SuppressWarnings("rawtypes")
	@Override
	public <K, V> Cache<K, V> getCache(String arg0) throws CacheException {
		logger.info("根据key[{}]获取cache",arg0);
		Cache c = caches.get(arg0);
		if(c == null){
			c = new RedisCache<K, V>(redisManager);
			caches.put(arg0, c);
		}
		return c;
	}

	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

	
}
