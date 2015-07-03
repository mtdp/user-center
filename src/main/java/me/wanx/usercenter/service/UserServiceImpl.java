package me.wanx.usercenter.service;

import java.util.List;
import java.util.Map;

import me.wanx.common.core.persistence.BasePagination;
import me.wanx.common.core.utils.DateUtil;
import me.wanx.usercenter.UserCenterConstant;
import me.wanx.usercenter.bean.Role;
import me.wanx.usercenter.bean.User;
import me.wanx.usercenter.dao.IUserDao;
import me.wanx.usercenter.exception.UserCenterServiceException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private IUserDao<User> userDao;
	
	@Autowired
	private IRoleService roleService;
	
	@Override
	public User login(String account, String password)throws UserCenterServiceException {
		User u = userDao.login(account, password);
		if(null != u){
			return u;
		}
		return null;
	}
	
	@Override
	public boolean loginAction(String account, String password)throws UserCenterServiceException {
		try{
			UsernamePasswordToken token = new UsernamePasswordToken(account, password);
	        Subject subject = SecurityUtils.getSubject();
	        subject.login(token);
		}catch(Exception e){
			logger.error("登录失败",e);
			throw new UserCenterServiceException("登录失败");
		}
		return true;
	}
	
	@Override
	public boolean updatePassord(Integer userId) throws UserCenterServiceException {
		int c = userDao.updatePassword(UserCenterConstant.DEFAULT_PASSWORD, userId);
		if(c > 0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean save(User user) throws UserCenterServiceException {
		//如果状态为空则设置成功禁用
		if(null == user.getStatus() || user.getStatus().isEmpty()){
			user.setStatus(UserCenterConstant.STATUS_OFF);
		}
		user.setCreateTime(DateUtil.getCurrentTime());
		user.setUpdateTime(DateUtil.getCurrentTime());
		//如果密码为空设置默认密码
		if(null == user.getPassword() || user.getAccount().isEmpty()){
			user.setPassword(UserCenterConstant.DEFAULT_PASSWORD);
		}
		int c = userDao.add(user);
		if(c > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean update(User user) throws UserCenterServiceException {
		if(null == user || user.getUserId() == null){
			return false;
		}
		//如果状态为空则设置成功禁用
		if(null == user.getStatus() || user.getStatus().isEmpty()){
			user.setStatus(UserCenterConstant.STATUS_OFF);
		}
		user.setUpdateTime(DateUtil.getCurrentTime());
		int c = userDao.update(user);
		if(c > 0){
			saveOrUpdateUserRoles(user.getUserId(),user.getRoles());
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(User user) throws UserCenterServiceException {
		int c = userDao.del(user.getUserId());
		if(c > 0){
			//删除用户关联的角色信息
			userDao.delRoles(user.getUserId());
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Integer userId) throws UserCenterServiceException {
		int c = userDao.del(userId);
		if(c > 0){
			//删除用户关联的角色信息
			userDao.delRoles(userId);
			return true;
		}
		return false;
	}

	@Override
	public User getUser(Integer userId) throws UserCenterServiceException {
		User u = userDao.get(userId);
		List<Role> roles = roleService.getRoles(userId);
		u.setRoles(roles);
		return u;
	}

	@Override
	public List<User> getUser4UserName(String userName)throws UserCenterServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUser4Mobile(String mobile)
			throws UserCenterServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUser4Email(String email)
			throws UserCenterServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean saveOrUpdateUserRoles(Integer userId, List<Role> roles)throws UserCenterServiceException {
		//用户关联角色不会太多，也不会频繁变动,故先删除关联的角色,再添加角色 TODO
		int dc = userDao.delRoles(userId);
		if(roles == null || roles.isEmpty()){
			//保存或者接触用户所有权限
			return true;
		}
		int ac = userDao.addUserRoles(userId, roles);
		if(ac == roles.size()){
			//add数量等于list角色数量更新成功
			return true;
		}
		return false;
	}
	
	@Override
	public List<Role> getUserRoles4User(User user) throws UserCenterServiceException {
		if(null != user){
			return userDao.getUserRoles(user.getUserId());
		}
		return null;
	}
	
	@Override
	public void searchUserPagination(BasePagination<User> userPage) throws UserCenterServiceException {
		logger.info("用户分页开始");
		//用户总数
		int totalUser = userDao.searchCountUser(userPage.getOtherQueryParams());
		userPage.setTotalRecord(totalUser);
		//查询条件
		Map<String, Object> param = userPage.getQueryParams();
		param.put("sort", "updateTime");
		List<User> users = userDao.searchUserPagination(param);
		userPage.setResults(users);
	}
	
	@Override
	public User getCurrentLoginUser() {
		return (User)SecurityUtils.getSubject().getSession().getAttribute(UserCenterConstant.LOGIN_USER);
	}

}
