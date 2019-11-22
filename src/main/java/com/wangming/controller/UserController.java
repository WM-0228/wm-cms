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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.wangming.common.CmsAssert;
import com.wangming.common.ConstantClass;
import com.wangming.common.MsgResult;
import com.wangming.entity.Article;
import com.wangming.entity.Category;
import com.wangming.entity.Channel;
import com.wangming.entity.User;
import com.wangming.service.ArticleService;
import com.wangming.service.ChannelService;
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

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ChannelService channelService;
	
	//資源文件赋值操作   将文件路径值获取
	@Value("${upload.path}")
	private String uploadPath;
	
	Logger log = Logger.getLogger(UserController.class);
	
	@RequestMapping("login")
	public String login(){
		return "/user/login";
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 用户登入 接收post请求
	 * @param user
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(User user,HttpServletRequest request){
		User login = userService.login(user);
		if(login != null){
			request.getSession().setAttribute(ConstantClass.USER_KEY, login);
			return login.getRole() == ConstantClass.USER_ROLE_GENERAL ? "redirect:/user/user_interface" : "redirect:/admin/index";
		}else{
			request.setAttribute("user",user);
			return "/user/login";
		}
	}
	
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
		System.out.println("------------------------------------------");
		User existUser = userService.findByName(user.getUsername());
		CmsAssert.AssertTrue(existUser == null, "该用户名已存在");
		
		int resul = userService.register(user);
		CmsAssert.AssertTrue(resul > 0, "用戶注冊失敗,请稍后再试");
		
		return "/user/login";
	}
	
	/**
	 * 
	 * @Title: checkName 
	 * @Description: 用于validata验证用户唯一
	 * @param username
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("checkName")
	public boolean checkName(String username){
		return null == userService.findByName(username);
	}
	
	/**
	 * 
	 * @Title: exitLogin 
	 * @Description: 退出登入
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping("exit")
	public String exitLogin(HttpServletRequest request,String flag){
		request.getSession().removeAttribute(ConstantClass.USER_KEY);
		return flag.equals("index") ? "redirect:/" : "redirect:/user/login";
	}
	
	/**
	 * 
	 * @Title: user_interface 
	 * @Description: 用户页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("user_interface")
	public String user_interface(){
		return "/user/user_interface";
	}
	
	/**
	 * 
	 * @Title: myArticle 
	 * @Description: 获取当前用户的所有文章
	 * @param userId
	 * @param pageNum
	 * @param m
	 * @return
	 * @return: String
	 */
	@RequestMapping("myArticle")
	public String myArticle(Integer userId,@RequestParam(defaultValue="1")int pageNum,Model m){
		PageInfo page = articleService.getUserById(userId,pageNum);
		m.addAttribute("info", page);
		return "/user/myArticle";
	}
	/**
	 * 
	 * @Title: deleteArticle 
	 * @Description: 删除文章
	 * @param id
	 * @param request
	 * @return
	 * @return: Object
	 */
	@ResponseBody
	@RequestMapping("deleteArticle")
	public Object deleteArticle(Integer id,HttpServletRequest request){
		System.out.println("=================================="+id);
		CmsAssert.AssertTrue(id > 0, "id必须大于0");
		//根据文章ID去查找文章,看文章是否存在 。。。
		Article articleList = articleService.getById(id);
		CmsAssert.AssertTrue(articleList != null,"该文章不存在");
		
		//获取session里的user对象
		User user = (User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		
		
		//id 应该是查询后的对象id  此处省略
		CmsAssert.AssertTrue(user.getRole() == ConstantClass.USER_ROLE_ADMIN || 
				user.getId() == articleList.getUserId()
				, "只有该用户和管理员可以删除");
		
		boolean deleteArticle = userService.deleteArticle(id);
		if(deleteArticle){
			return new MsgResult(1,"删除成功",null);
		}else{
			return new MsgResult(0,"删除失败",null);
		}
	}
	
	/**
	 * 
	 * @Title: publishArticle 
	 * @Description: 获取频道并跳转到发布文章列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("publish")
	public String publishArticle(Model m){
		List<Channel> channelList = channelService.getList();
		m.addAttribute("channels",channelList);
		return "/article/publish";
	}
	
	/**
	 * 
	 * @Title: getChannelId 
	 * @Description: 根据频道Id获取频道分类
	 * @param channelId
	 * @return
	 * @return: String
	 */
	@RequestMapping("getChannelId")
	@ResponseBody
	public MsgResult getChannelId(Integer channelId){
		List<Category> channelId2 = userService.getChannelId(channelId);
		if(!channelId2.isEmpty()){
			return new MsgResult(1, "获取成功", channelId2);
		}else{
			return new MsgResult(2, "获取失败", null);
		}
	}
	
	/**
	 * 
	 * @Title: publishArticle 
	 * @Description: 用户发布文章
	 * @param file
	 * @param article
	 * @param request
	 * @return
	 * @throws Exception
	 * @return: MsgResult
	 */
	@RequestMapping("publishArticle")
	@ResponseBody
	public MsgResult publishArticle(MultipartFile file,Article article,HttpServletRequest request) throws Exception{
		if(!file.isEmpty()){
			String newUrl = processFile(file);
			article.setPicture(newUrl);
		}
		//从作用域中获取当前登入的用户id
		User user = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		article.setUserId(user.getId());
		//添加
		System.out.println("=========================="+article);
		int addUserArticle = userService.addUserArticle(article);
		if(addUserArticle > 0){
			return new MsgResult(1,"发布成功",null);
		}else{
			return new MsgResult(2,"发布失败",null);
		}
	}
	
	/**
	 * 
	 * @Title: publishArticle 
	 * @Description: 用户发布文章
	 * @param file
	 * @param article
	 * @param request
	 * @return
	 * @throws Exception
	 * @return: MsgResult
	 */
	@RequestMapping("userUpdateArticle")
	@ResponseBody
	public MsgResult userUpdateArticle(MultipartFile file,Article article) throws Exception{
		if(!file.isEmpty()){
			String newUrl = processFile(file);
			article.setPicture(newUrl);
		}
		//执行修改
		int updateUserArticle = userService.updateUserArticle(article);
		if(updateUserArticle > 0){
			return new MsgResult(1,"修改成功",null);
		}else{
			return new MsgResult(2,"修改失败",null);
		}
	}
	
	
	/**
	 * 
	 * @Title: processFile 
	 * @Description: 用于处理文件返回新的地址和图片名
	 * @param file
	 * @return
	 * @return: String
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	private String processFile(MultipartFile file) throws IllegalStateException, IOException{
		//获取源文件后缀
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		//生成新的文件名称
		String fileName = UUID.randomUUID().toString();
		
		//拼接新的文件名称
		String newFileName = suffix + fileName;
		//获取当前日期当做新的文件夹名称
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String newFolder = sdf.format(new Date());
		//將新的文件夾名称放入File对象里  用于创建新的对象
		File myFile = new File(uploadPath+"/"+newFolder);
		if(!myFile.exists()){
			myFile.mkdir();
		}

		//将文件保存到该路径下
		file.transferTo(new File(uploadPath+"/"+newFolder+"/"+newFileName));
		//返回新的文件夹名和新的图片名称
		return newFolder + "/" + newFileName;
	}
	
	@RequestMapping("toUserUpdateArticle")
	public String toUserUpdateArticle(Integer articleId,HttpServletRequest request){
		//获取所有的频道
		List<Channel> channelList = channelService.getList();
		request.setAttribute("channels",channelList);
		//根据文章id获取文章内容用于回显
		Article detailArticle = articleService.getDetailArticle(articleId);
		request.setAttribute("article", detailArticle);
		request.setAttribute("content1",detailArticle.getContent());
		return "/article/userUpdate";
	}
	
	
}
