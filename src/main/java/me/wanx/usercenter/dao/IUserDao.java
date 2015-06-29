package me.wanx.usercenter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import me.wanx.common.core.persistence.BaseMapper;
import me.wanx.usercenter.bean.Role;
import me.wanx.usercenter.bean.User;

/**
 * 
* @ClassName: IUserDao 
* @Description: TODO 
* @author gqwang
* @date 2015年6月18日 下午5:21:32 
*
 */

public interface IUserDao<T extends User> extends BaseMapper<T> {
	
	/**
	 * 登录
	 * @param account
	 * @param password
	 * @return
	 */
	public User login(@Param("account") String account,@Param("password") String password);
	
	/**
	 * 删除userId下所有角色
	 * @param userId
	 * @return
	 */
	public int delRoles(Integer userId);
	
	/**
	 * 批量保存用户角色
	 * @param userId
	 * @param roles
	 * @return
	 */
	public int addUserRoles(@Param("userId")Integer userId,@Param("roles")List<Role> roles);
	
	/**
	 * 获取用户下所有角色
	 * @param userId
	 * @return
	 */
	public List<Role> getUserRoles(Integer userId);
	
	/**
	 * 获取用户下所有角色code
	 * @param userId
	 * @return
	 */
	public List<String> getUserRoleCode(Integer userId);
	
	public List<User> getUser4UserName(String userName);
	
	/**
	 * 用户分页
	 * @param map
	 * @return
	 */
	public List<User> searchUserPagination(Map<String,Object> map);
	public Integer searchCountUser(Map<String,Object> map);

}
