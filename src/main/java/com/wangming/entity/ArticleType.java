/**   
 * 
 * @Title: ArticleType.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.entity 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月22日 下午3:35:59 
 * @version: V1.0   
 */
package com.wangming.entity;

/** 
 * @ClassName: ArticleType 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月22日 下午3:35:59  
 */
public enum ArticleType {
	
	HTML,IMG;
	
	public int getOrdinal(){
		return this.ordinal();
	}
}

