package me.wanx.usercenter.shiro.cache;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import me.wanx.common.core.persistence.RedisManager;
import me.wanx.common.core.utils.SerializeUtils;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 实现shiro EnterpriseCacheSessionDAO 对sessionDAO功能
* @ClassName: RedisSessionDAO 
* @Description: TODO 
* @author gqwang
* @date 2015年8月6日 下午1:43:35 
*
 */
public class RedisSessionDAO extends AbstractSessionDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);
	
	private RedisManager redisManager;

	@Override
	public void delete(Session arg0) {
		if(arg0 == null || arg0.getId() == null){
			logger.error("删除session失败 ");
			return;
		}
		Serializable ser = arg0.getId();
		String id = CacheConstant.KEY_PRE_FIX + ser; 
		redisManager.del(id);
		logger.info("成功删除key为[{}]的session",id);
	}

	@Override
	public Collection<Session> getActiveSessions() {
		Collection<Session> sessions = new HashSet<Session>();
		String pattern = CacheConstant.KEY_PRE_FIX + "*";
		Set<byte[]> keys = this.redisManager.keys(pattern.getBytes());
		if(keys != null && keys.size() > 0){
			for(byte[] key : keys){
				byte[] bytes = this.redisManager.get(key);
				Session s = (Session)SerializeUtils.deserialize(bytes);
				sessions.add(s);
			}
		}
		return sessions;
	}

	@Override
	public void update(Session arg0) throws UnknownSessionException {
		logger.info("更新session[{}]",arg0.getId());
		this.saveSession(arg0);
	}

	@Override
	protected Serializable doCreate(Session arg0) {
		logger.info("保存session[{}]",arg0.getId());
		Serializable sessionId = this.generateSessionId(arg0);
		//绑定sessionId
		this.assignSessionId(arg0, sessionId);
		this.saveSession(arg0);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable arg0) {
		if(arg0 == null){
			logger.error("sessionId 不能为空");
			return null;
		}
		String key = CacheConstant.KEY_PRE_FIX + arg0;
		byte[] bytes =this.redisManager.get(key.getBytes());
		return (Session)SerializeUtils.deserialize(bytes);
	}
	
	/**
	 * 保存session到缓存
	 * @param session
	 */
	private void saveSession(Session session){
		if(session == null || session.getId() == null){
			logger.error("保存的session不能为空");
			return;
		}
		String key = CacheConstant.KEY_PRE_FIX + session.getId();
		//单位毫秒
		session.setTimeout(this.redisManager.getExpire()*1000);
		this.redisManager.set(key.getBytes(), SerializeUtils.serialize(session),this.redisManager.getExpire());
		logger.info("成功保存key为[{}]的session",key);
	}

	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}
	
}
