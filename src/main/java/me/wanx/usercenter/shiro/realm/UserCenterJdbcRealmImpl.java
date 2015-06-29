package me.wanx.usercenter.shiro.realm;

import me.wanx.usercenter.bean.User;
import me.wanx.usercenter.exception.UserCenterServiceException;
import me.wanx.usercenter.service.IUserService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 
 * @ClassName: package-info 
 * @Description: TODO 
 * @author gqwang
 * @date 2015年6月18日 下午4:45:14 
 *  
 */
@Service
public class UserCenterJdbcRealmImpl implements Realm{
	private static final Logger logger = LoggerFactory.getLogger(UserCenterJdbcRealmImpl.class);

	@Autowired
	private IUserService userService;
	
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)throws AuthenticationException {
		//验证用户名
		String userName = (String)token.getPrincipal();
		logger.info("userName="+userName);
		//验证密码
		String password = new String((char[])token.getCredentials());
		logger.info("passwod="+password);
		//TODO 
		try {
			User u = userService.login(userName, password);
			if(null == u){
				throw new AuthenticationException("登录失败");
			}
		} catch (UserCenterServiceException e) {
			e.printStackTrace();
		}
		return new SimpleAuthenticationInfo(userName, password, getName());
	}

	@Override
	public String getName() {
		return "UserCenterRealm";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}
	
}
