package me.wanx.usercenter.dao.domain;

import java.io.Serializable;

/**
 * 
* @ClassName: UserRoleTbl 
* @Description: TODO 
* @author gqwang
* @date 2015年6月19日 上午9:10:11 
*
 */
public class UserRoleTbl implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userRoleId;
	private Integer userId;
	private Integer roleId;
	private String createTime;
	private String updateTime;
	
	public Integer getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
