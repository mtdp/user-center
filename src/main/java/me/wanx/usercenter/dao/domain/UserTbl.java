package me.wanx.usercenter.dao.domain;

import java.io.Serializable;

/**
 * 
* @ClassName: UserTbl 
* @Description: TODO 
* @author gqwang
* @date 2015年6月18日 下午4:37:07 
*
 */
public class UserTbl implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String userName;
	private String account;
	private String password;
	private String mobile;
	private String email;
	/** 1=启用,0=禁用 **/
	private String status;
	private String createTime;
	private String updateTime;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
