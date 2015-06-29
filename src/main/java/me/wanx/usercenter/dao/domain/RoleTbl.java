package me.wanx.usercenter.dao.domain;

import java.io.Serializable;

/**
 * 
* @ClassName: RoleTbl 
* @Description: TODO 
* @author gqwang
* @date 2015年6月19日 上午9:11:53 
*
 */
public class RoleTbl implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer roleId;
	private String roleName;
	private String roleCode;
	/** 1=启用,0=禁用 **/
	private String status;
	/** 创建用户id **/
	private Integer createByUserId;
	private Integer lastUpdateUserId;
	private String createTime;
	private String updateTime;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCreateByUserId() {
		return createByUserId;
	}
	public void setCreateByUserId(Integer createByUserId) {
		this.createByUserId = createByUserId;
	}
	public Integer getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	public void setLastUpdateUserId(Integer lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
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
