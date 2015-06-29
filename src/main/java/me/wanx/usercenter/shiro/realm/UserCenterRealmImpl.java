package me.wanx.usercenter.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.wanx.usercenter.UserCenterConstant;
import me.wanx.usercenter.bean.Role;
import me.wanx.usercenter.bean.User;
import me.wanx.usercenter.exception.UserCenterServiceException;
import me.wanx.usercenter.service.IRoleService;
import me.wanx.usercenter.service.IUserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 
 * @ClassName: UserCenterRealmImpl
 * @Description: TODO 
 * @author gqwang
 * @date 2015年6月18日 下午4:45:14 
 *  
 */
@Service
public class UserCenterRealmImpl extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(UserCenterRealmImpl.class);
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	
	/**
	 *  获取身份 获取权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
//		String userName = (String)arg0.getPrimaryPrincipal();
		//获取的是登录帐号
		User user = userService.getCurrentLoginUser();
		logger.info("PrincipalCollection user="+user);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();  
		Set<String> set = new HashSet<String>();
		Set<String> stringPermissions = new HashSet<String>();
		try {
			List<Role> roles = userService.getUserRoles4User(user);
			for(Role r : roles){
				set.add(r.getRoleCode());
				//查询资源 可以用in一次查询TODO
				List<String> resCodes = roleService.getRoleResCode(r.getRoleId());
				stringPermissions.addAll(resCodes);
			}
		} catch (UserCenterServiceException e) {
			logger.error("根据用户名查询角色出错",e);
			return null;
		}
		//设置角色code
		authorizationInfo.setRoles(set);
		//设置资源code
		authorizationInfo.setStringPermissions(stringPermissions);
		return authorizationInfo;
	}
	
	/**
	 *  授权信息 登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//验证用户名
		String userName = (String)token.getPrincipal();
		logger.info("userName="+userName);
		//验证密码
		String password = new String((char[])token.getCredentials());
		logger.info("passwod="+password);
		try {
			User u = userService.login(userName, password);
			if(null == u){
				throw new AuthenticationException("登录失败,用户名或密码错误");
			}
			//TODO 是否保存用户信息在session中
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute(UserCenterConstant.LOGIN_USER, u);
		} catch (UserCenterServiceException e) {
			logger.error("用户登录出错",e);
			throw new AuthenticationException("登录失败,内部错误");
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
