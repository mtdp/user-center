package me.wanx.usercenter.dao.domain;

import java.io.Serializable;

/**
 * 
* @ClassName: ResourceTbl 
* @Description: TODO 
* @author gqwang
* @date 2015年6月19日 上午9:13:43 
*
 */
public class ResourceTbl implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer resId;
	private String resName;
	private String resURI;
	private String resCode;
	/** 1=是菜单,0=不是菜单 **/
	private String isMenu;
	/** 1=启用,0=禁用**/
	private String status;
	/** 排序 **/
	private Integer rank;
	private String createTime;
	private String updateTime;
	public Integer getResId() {
		return resId;
	}
	public void setResId(Integer resId) {
		this.resId = resId;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResURI() {
		return resURI;
	}
	public void setResURI(String resURI) {
		this.resURI = resURI;
	}
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	public String getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
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
