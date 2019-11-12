/**   
 * 
 * @Title: UserController.java 
 * @Prject: wangming-cms
 * @Package: com.wangming.controller 
 * @Description: TODO
 * @author: WM  
 * @date: 2019年11月12日 上午11:28:06 
 * @version: V1.0   
 */
package com.wangming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @ClassName: UserController 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月12日 上午11:28:06  
 */
@Controller
@RequestMapping("user")
public class UserController {

	@RequestMapping("login")
	public String login(Model m){
		m.addAttribute("hello","你好");
		return "user/login";
	}
}
