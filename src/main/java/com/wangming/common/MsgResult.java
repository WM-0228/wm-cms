/**   
 * 
 * @Title: MsgResult.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.common 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月13日 下午2:13:17 
 * @version: V1.0   
 */
package com.wangming.common;

import java.io.Serializable;

/** 
 * @ClassName: MsgResult 
 * @Description: 用来传入数据
 * @author:WM 
 * @date: 2019年11月13日 下午2:13:17  
 */
public class MsgResult implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -1771418229605341526L;
	
	private int result;
	
	private String errorMsg;
	
	private Object data;

	/** 
	 * @Title:MsgResult
	 * @Description:TODO 
	 * @param result
	 * @param errorMsg
	 * @param data 
	 */
	public MsgResult(int result, String errorMsg, Object data) {
		super();
		this.result = result;
		this.errorMsg = errorMsg;
		this.data = data;
	}

	/** 
	 * @Title:MsgResult
	 * @Description:TODO  
	 */
	public MsgResult() {
		super();
	}

	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	
}
