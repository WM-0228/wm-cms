/**   
 * 
 * @Title: CmsException.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.common 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月15日 下午6:33:51 
 * @version: V1.0   
 */
package com.wangming.common;

/** 
 * @ClassName: CmsException 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月15日 下午6:33:51  
 */
public class CmsException extends RuntimeException {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	public CmsException(String msg){
		super(msg);
	}
}
