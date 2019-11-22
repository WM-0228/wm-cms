/**   
 * 
 * @Title: CmsAssert.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.common 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月15日 下午6:32:04 
 * @version: V1.0   
 */
package com.wangming.common;

/** 
 * @ClassName: CmsAssert 
 * @Description: 断言类
 * @author:WM 
 * @date: 2019年11月15日 下午6:32:04  
 */
public class CmsAssert {

	
	public static void AssertTrue(boolean express,String msg){
		if(!express)
			throw new CmsException(msg);
	}
	
	public static void AssertTrueHtml(boolean express,String msg){
		if(!express)
			throw new CmsExceptionHtml(msg);
	}
}
