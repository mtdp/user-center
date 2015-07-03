package me.wanx.usercenter.action.bean;

import java.io.Serializable;

/**
 * 
* @ClassName: ActionResultMessage 
* @Description: TODO 
* @author gqwang
* @date 2014年11月14日 上午11:54:48 
*
 */
public class ActionResultMessage implements Serializable {
	private static final long serialVersionUID = -2753307512189953499L;
	
	private String code;
	private String info;
	private Object obj;
	
	public ActionResultMessage(){
		
	}
	
	public ActionResultMessage(String code ,String info){
		this.code = code;
		this.info = info;
	}
	
	public ActionResultMessage(String code ,String info,Object obj){
		this.code = code;
		this.info = info;
		this.obj = obj;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
