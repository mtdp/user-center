package me.wanx.usercenter.service;

import java.util.List;
import java.util.Map;

import me.wanx.common.core.persistence.BasePagination;
import me.wanx.common.core.utils.DateUtil;
import me.wanx.usercenter.UserCenterConstant;
import me.wanx.usercenter.bean.Resource;
import me.wanx.usercenter.bean.Role;
import me.wanx.usercenter.bean.User;
import me.wanx.usercenter.dao.IRoleDao;
import me.wanx.usercenter.exception.UserCenterServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
* @ClassName: RoleServiceImpl 
* @Description: TODO 
* @author gqwang
* @date 2015年6月19日 下午3:31:22 
*
 */
@Service
public class RoleServiceImpl implements IRoleService {
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private IRoleDao<Role> roleDao;
	@Autowired
	private IUserService userService;
	
	@Override
	public boolean save(Role role) {
		if(null == role.getStatus() ||role.getStatus().isEmpty()){
			role.setStatus(UserCenterConstant.STATUS_OFF);
		}
		role.setCreateTime(DateUtil.getCurrentTime());
		role.setUpdateTime(DateUtil.getCurrentTime());
		//获取当前登录用户
		User u = userService.getCurrentLoginUser();
		if(null != u){
			role.setCreateByUserId(u.getUserId());
			role.setLastUpdateUserId(u.getUserId());
		}
		int c = roleDao.add(role);
		if(c > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Role role) {
		if(null == role || role.getRoleId() == null){
			return false;
		}
		//如果状态为空则设置成功禁用
		if(null == role.getStatus() || role.getStatus().isEmpty()){
			role.setStatus(UserCenterConstant.STATUS_OFF);
		}
		role.setUpdateTime(DateUtil.getCurrentTime());
		//获取当前登录用户
		User u = userService.getCurrentLoginUser();
		if(u != null){
			role.setLastUpdateUserId(u.getUserId());
		}else{
			//TODO 抛异常
		}
		int c = roleDao.update(role);
		if(c > 0){
			//TODO 
			//saveOrUpdateRoleResource(role.getRoleId(), role.getResources());
			return true;
		}
		return false;
	}
	
	@Override
	public List<Role> getAllRole() {
		return roleDao.gets();
	}

	@Override
	public Role getRole(Integer roleId) {
		return roleDao.get(roleId);
	}

	@Override
	public List<Role> getRoles(String roleName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getRoleUsers(Integer roleId) {
		return roleDao.getRoleUsers(roleId);
	}
	
	@Override
	public List<Role> getRoles(Integer userId) {
		return roleDao.getRoles(userId);
	}

	@Override
	public List<Resource> getRoleResource(Integer roleId) {
		return roleDao.getRoleResources(roleId);
	}

	@Override
	public boolean delete(Role role) {
		int c = roleDao.del(role.getRoleId());
		if(c > 0){
			//删除角色关联的资源信息
			roleDao.delRescources(role.getRoleId());
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Integer roleId) {
		int c = roleDao.del(roleId);
		if(c > 0){
			//删除角色关联的资源信息
			roleDao.delRescources(roleId);
			return true;
		}
		return false;
	}

	@Override
	public boolean saveOrUpdateRoleResource(Integer roleId,List<Resource> resources) {
		//先删除角色所有的资源
		int rc = roleDao.delRescources(roleId);
		if(resources == null || resources.isEmpty()){
			//移除此角色所有的资源
			return true;
		}
		int ac = roleDao.addRoleRescources(roleId, resources);
		if(ac == resources.size()){
			return true;
		}
		return false;
	}

	@Override
	public List<String> getRoleResCode(Integer roleId) {
		return roleDao.getRoleResCode(roleId);
	}
	
	@Override
	public void searchRolePagination(BasePagination<Role> rolePage)throws UserCenterServiceException {
		logger.info("角色分页开始");
		//用户总数
		int totalRole = roleDao.searchCountRole(rolePage.getOtherQueryParams());
		rolePage.setTotalRecord(totalRole);
		//查询条件
		Map<String, Object> param = rolePage.getQueryParams();
		param.put("sort", "updateTime");
		List<Role> roles = roleDao.searchRolePagination(param);
		rolePage.setResults(roles);
	}

}
