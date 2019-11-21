/**   
 * 
 * @Title: Md5.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.common 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月18日 下午3:47:28 
 * @version: V1.0   
 */
package com.wangming.common;

import org.apache.commons.codec.digest.DigestUtils;

/** 
 * @ClassName: Md5 
 * @Description: 给密码加盐
 * @author:WM 
 * @date: 2019年11月18日 下午3:47:28  
 */
public class Md5 {

	//获取用户密码和盐  进行加盐操作
	public static String password(String password,String salt){
		return DigestUtils.md5Hex(password+"::::"+salt);
	}
}
