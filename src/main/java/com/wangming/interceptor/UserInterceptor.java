/**   
 * 
 * @Title: UserInterceptor.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.interceptor 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月18日 下午4:04:40 
 * @version: V1.0   
 */
package com.wangming.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.wangming.common.ConstantClass;
import com.wangming.entity.User;

/** 
 * @ClassName: UserInterceptor 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月18日 下午4:04:40  
 */
public class UserInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		if(user == null){
			request.setAttribute("error","先登入吧兄弟");
			request.getRequestDispatcher("/user/login").forward(request, response);
			return false;
		}
		if(request.getServletPath().contains("/admin/") && user.getRole() == ConstantClass.USER_ROLE_GENERAL){
			request.setAttribute("error","请使用管理员账号登入");
			request.getRequestDispatcher("/user/login").forward(request, response);
			return false;
		}
		return true;
	}
}
