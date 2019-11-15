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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wangming.common.MsgResult;
import com.wangming.entity.User;
import com.wangming.service.UserService;
import com.wangming.utils.PageUtil;

/** 
 * @ClassName: UserController 
 * @Description: TODO
 * @author:WM 
 * @date: 2019年11月12日 上午11:28:06  
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("index")
	public String index(){
		return "admin/index";
	}
	
	@RequestMapping("users")
	public String users(Model m,@RequestParam(defaultValue="")String name,@RequestParam(defaultValue="1")int pageNum){
		System.out.println("------------------------------------------");
		PageInfo page = userService.userList(name, pageNum);
		m.addAttribute("info",page);
		m.addAttribute("name",name);
		return "/admin/user/userList";
	}
	
	@ResponseBody
	@RequestMapping("upUserLocked")
	public Object upUserLocked(Integer userId,Integer locked){
		System.out.println("------------------------------"+userId);
		if(locked != 0 && locked != 1){
			return new MsgResult(2,"操作无效",null);
		}
		
		User user = userService.getUser(userId);
		System.out.println("------------------------------"+user);
		if(user == null){
			return new MsgResult(3,"该用户不存在",null);
		}
		
		if(user.getLocked() == locked){
			return new MsgResult(4,"该用户无需做该操作",null);
		}
		
		boolean upUserStatus = userService.upUserStatus(userId, locked);
		if(upUserStatus){
			return new MsgResult(1,"修改成功",null);
		}else{
			return new MsgResult(1,"对不起,修改失败",null);
		}
	}
}
