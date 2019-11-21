/**   
 * 
 * @Title: CmsExceptionHtml.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.common 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月19日 下午1:40:23 
 * @version: V1.0   
 */
package com.wangming.common;

/** 
 * @ClassName: CmsExceptionHtml 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月19日 下午1:40:23  
 */
public class CmsExceptionHtml extends RuntimeException{

	private static final long serialVersionUID = -306329034146163814L;

	public CmsExceptionHtml(String msg){
		super(msg);
	}
}
