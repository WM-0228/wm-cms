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
import com.wangming.common.CmsAssert;
import com.wangming.common.MsgResult;
import com.wangming.entity.Article;
import com.wangming.entity.User;
import com.wangming.service.ArticleService;
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
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("index")
	public String index(){
		return "admin/index";
	}
	
	/**
	 * 
	 * @Title: users 
	 * @Description: 获取所有的用户信息
	 * @param m
	 * @param name
	 * @param pageNum
	 * @return
	 * @return: String
	 */
	@RequestMapping("users")
	public String users(Model m,@RequestParam(defaultValue="")String name,@RequestParam(defaultValue="1")int pageNum){
		PageInfo page = userService.userList(name, pageNum);
		m.addAttribute("info",page);
		m.addAttribute("name",name);
		return "/admin/user/userList";
	}
	
	/**
	 * 
	 * @Title: upUserLocked 
	 * @Description: 管理员修改状态
	 * @param userId
	 * @param locked
	 * @return
	 * @return: Object
	 */
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
	
	
	@RequestMapping("articles")
	public String articles(Model m,@RequestParam(defaultValue="1")int pageNum,@RequestParam(defaultValue="-1")int status){
		PageInfo articleList = articleService.getArticleList(status, pageNum);
		m.addAttribute("info",articleList);
		/*articleList.getList().forEach(x -> System.out.println("===================="+x));*/
		return "/admin/article/list";
	}
	
	@RequestMapping("detailArticle")
	@ResponseBody
	public Object detailArticle(Integer id){
		Article articleList = articleService.getDetailArticle(id);
		CmsAssert.AssertTrue(articleList != null,"该文章不存在");
		return new MsgResult(1,"獲取成功",articleList);
	}
	
	@RequestMapping("updateStatus")
	@ResponseBody
	public Object updateStatus(Integer status,Integer id){
		//如果该用户已经是此状态就抛出异常
		
		/*System.out.println("============================"+status+"========================="+id);
		Article article = articleService.getById(id);
		CmsAssert.AssertTrue(article.getStatus() != status,"该用户无需做该操作");
		CmsAssert.AssertTrue(article.getHot() != (status == 3 ? 1 : status == 4 ? 0 : 1),"该用户无需做该操作");*/
		
		boolean updateStatus = articleService.updateStatus(status, id);
		
		
		if(updateStatus){
			return new MsgResult(1,"修改成功",null);
		}else{
			return new MsgResult(2,"修改失败",null);
		}
	}
	
}
