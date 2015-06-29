package me.wanx.usercenter.dao.domain;

import java.io.Serializable;

/**
 * 
* @ClassName: RoleResTbl 
* @Description: TODO 
* @author gqwang
* @date 2015年6月19日 上午9:15:40 
*
 */

public class RoleResTbl implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer roleResId;
	private Integer roleId;
	private Integer resId;
	private String createTime;
	private String updateTime;
	public Integer getRoleResId() {
		return roleResId;
	}
	public void setRoleResId(Integer roleResId) {
		this.roleResId = roleResId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getResId() {
		return resId;
	}
	public void setResId(Integer resId) {
		this.resId = resId;
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
