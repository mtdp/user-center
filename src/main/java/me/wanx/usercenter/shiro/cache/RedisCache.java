package me.wanx.usercenter.shiro.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.wanx.common.core.persistence.RedisManager;
import me.wanx.common.core.utils.SerializeUtils;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: RedisCache 
 * @Description: TODO 
 * @author gqwang
 * @param <K>
 * @param <V>
 * @date 2015年8月6日 下午4:22:30 
 *  
 */
public class RedisCache<K, V> implements Cache<K, V> {

	private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
	
	private RedisManager redisManager;
	
	public RedisCache(){}
	
	public RedisCache(RedisManager redisManager){
		if(redisManager == null){
			throw new IllegalArgumentException("redisManager cannot be null.");
		}
		this.redisManager = redisManager;
	}
	
	@Override
	public void clear() throws CacheException {
		this.redisManager.delCurrentAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(K arg0) throws CacheException {
		byte[] key = this.getKey(arg0);
		byte[] value = this.redisManager.get(key);
		V v = (V)SerializeUtils.deserialize(value);
		return v;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
		String pattern = CacheConstant.KEY_PRE_FIX + "*";
		Set<byte[]> keys = this.redisManager.keys(pattern.getBytes());
		Set<K> set = new HashSet<K>();
		for(byte[] key : keys){
			set.add((K)key);
		}
		return set;
	}

	@Override
	public V put(K arg0, V arg1) throws CacheException {
		this.redisManager.set(this.getKey(arg0), SerializeUtils.serialize(arg1));
		return arg1;
	}

	@Override
	public V remove(K arg0) throws CacheException {
		V v = this.get(arg0);
		this.redisManager.del(this.getKey(arg0));
		return v;
	}

	@Override
	public int size() {
		return this.redisManager.DBSize().intValue();
	}

	@Override
	public Collection<V> values() {
		Set<K> set = this.keys();
		List<V> list = new ArrayList<V>();
		for(K k : set){
			V v = this.get(k);
			list.add(v);
		}
		return list;
	}
	
	/**
	 * 将key转化成byte数组
	 * @param key
	 * @return
	 */
	private byte[] getKey(K key){
		if(key instanceof String){
			return (CacheConstant.KEY_PRE_FIX + key).getBytes();
		}
		return SerializeUtils.serialize(key);
	}
}
