package me.wanx.usercenter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import me.wanx.common.core.persistence.BaseMapper;
import me.wanx.usercenter.bean.Resource;
import me.wanx.usercenter.bean.Role;
import me.wanx.usercenter.bean.User;

/**
 * 
* @ClassName: IRoleDao 
* @Description: TODO 
* @author gqwang
* @date 2015年6月19日 上午11:06:22 
* 
* @param <T>
 */
public interface IRoleDao<T extends Role> extends BaseMapper<T> {
	
	/**
	 * 获取角色下的所有资源
	 * @param roleId
	 * @return
	 */
	public List<Resource> getRoleResources(Integer roleId);
	
	public List<String> getRoleResCode(Integer roleId);
	
	/**
	 * 获取角色下的所有用户
	 * @param roleId
	 * @return
	 */
	public List<User> getRoleUsers(Integer roleId);
	
	/**
	 * 根据用户id查询角色
	 * @param userId
	 * @return
	 */
	public List<Role> getRoles(Integer userId);
	
	/**
	 * 保存角色下的资源
	 * @param roleId
	 * @param resources
	 * @return
	 */
	public int addRoleRescources(@Param("roleId")Integer roleId,@Param("resources")List<Resource> resources);
	
	/**
	 * 删除角色下的所有资源关联信息
	 * @param roleId
	 * @return
	 */
	public int delRescources(Integer roleId);
	
	/**
	 * 角色分页
	 * @param map
	 * @return
	 */
	public List<Role> searchRolePagination(Map<String,Object> map);
	public Integer searchCountRole(Map<String,Object> map);
}
