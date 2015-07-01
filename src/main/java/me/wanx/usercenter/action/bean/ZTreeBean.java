package me.wanx.usercenter.action.bean;

import java.io.Serializable;
import java.util.List;

public class ZTreeBean implements Serializable {
	private static final long serialVersionUID = 174555948815476672L;
	
	private Integer id;
	private Integer pId;
	private String name;
	private boolean open;
	private boolean checked;
	
	private List<ZTreeBean> subTree;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<ZTreeBean> getSubTree() {
		return subTree;
	}

	public void setSubTree(List<ZTreeBean> subTree) {
		this.subTree = subTree;
	}
	

}
