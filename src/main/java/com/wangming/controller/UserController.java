/**   
 * 
 * @Title: UserController.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.controller 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月15日 下午4:16:22 
 * @version: V1.0   
 */
package com.wangming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wangming.common.CmsAssert;
import com.wangming.entity.User;
import com.wangming.service.UserService;

/** 
 * @ClassName: UserController 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月15日 下午4:16:22  
 */
@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	/**
	 * 
	 * @Title: register 
	 * @Description: 接收get请求  返回注册页面
	 * @return
	 * @return: String
	 */
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String register(){
		return "/user/register";
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(Model m,User user){
		
		User existUser = userService.findByName(user.getUsername());
		CmsAssert.AssertTrue(existUser == null, "该用户名已存在");
		
		int resul = userService.register(user);
		CmsAssert.AssertTrue(resul > 0, "用戶注冊失敗,请稍后再试");
		
		return "";
	}
	
}
